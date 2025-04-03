package com.anaraliev.smilemanager.new_task

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaraliev.smilemanager.database.entity.BasePriceItem
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.database.entity.CustomerPriceItem
// Import UseCases
import com.anaraliev.smilemanager.new_task.domain.GetBasePricesUseCase
import com.anaraliev.smilemanager.new_task.domain.GetCustomerPricesUseCase
import com.anaraliev.smilemanager.new_task.domain.NewTaskGetCustomersUseCase
import com.anaraliev.smilemanager.new_task.domain.SaveBasePricesUseCase
import com.anaraliev.smilemanager.new_task.domain.SaveCustomerPricesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class PriceMode {
    object Base : PriceMode()
    data class Customer(val customerId: Int, val customerName: String) : PriceMode()
}

@OptIn(ExperimentalCoroutinesApi::class)
class NewTaskViewModel(
    // Inject UseCases instead of DAOs
    private val newTaskGetCustomersUseCase: NewTaskGetCustomersUseCase,
    private val getBasePricesUseCase: GetBasePricesUseCase,
    private val saveBasePricesUseCase: SaveBasePricesUseCase,
    private val getCustomerPricesUseCase: GetCustomerPricesUseCase,
    private val saveCustomerPricesUseCase: SaveCustomerPricesUseCase
) : ViewModel() {

    val customers: StateFlow<List<CustomerEntity>> = newTaskGetCustomersUseCase() // UseCase call
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _priceMode = MutableStateFlow<PriceMode>(PriceMode.Base)
    val priceMode: StateFlow<PriceMode> = _priceMode.asStateFlow()

    // Flow that holds the current base prices as a map for quick lookup
    // Use getBasePricesUseCase here
    private val basePricesMapFlow: Flow<Map<String, Double>> =
        getBasePricesUseCase() // UseCase call
            .map { list -> list.associateBy({ it.workName }, { it.price }) }
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000), replay = 1)

    private val _editablePriceItems = MutableStateFlow<List<PriceItemDisplay>>(emptyList())
    val currentPriceItems: StateFlow<List<PriceItemDisplay>> = _editablePriceItems.asStateFlow()

    val currentTitle: Flow<String> = _priceMode.map { mode ->
        when (mode) {
            is PriceMode.Base -> "Базовый Прайс"
            is PriceMode.Customer -> "Прайс: ${mode.customerName}"
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "Загрузка...")

    init {
        viewModelScope.launch {
            _priceMode.combine(basePricesMapFlow) { mode, baseMap -> Pair(mode, baseMap) }
                .flatMapLatest { (mode, baseMap) ->
                    when (mode) {
                        is PriceMode.Base -> fetchBasePriceItemsFlow(baseMap)
                        is PriceMode.Customer -> fetchCustomerPriceItemsFlow(mode.customerId, baseMap)
                    }
                }
                .catch { e -> Log.e("NewTaskViewModel", "Error collecting price items", e) }
                .collectLatest { items ->
                    if (_editablePriceItems.value != items) {
                        _editablePriceItems.value = items
                        Log.d("NewTaskViewModel", "Price items updated for mode: ${_priceMode.value}")
                    }
                }
        }
        loadBasePrice()
    }

    fun loadBasePrice() {
        _priceMode.value = PriceMode.Base
    }

    fun loadCustomerPrice(customer: CustomerEntity) {
        _priceMode.value = PriceMode.Customer(customer.uid, customer.customerName)
    }

    // --- Data Fetching Flows ---

    private fun fetchBasePriceItemsFlow(baseMap: Map<String, Double>): Flow<List<PriceItemDisplay>> {
        // Use the UseCase to get the data
        return getBasePricesUseCase() // UseCase call
            .map { baseItems ->
                baseItems.map {
                    PriceItemDisplay(
                        id = it.id, workName = it.workName, price = it.price,
                        isBaseItem = true, customerId = null,
                        basePriceForReference = it.price // Base reference is itself
                    )
                }
            }
    }

    private fun fetchCustomerPriceItemsFlow(customerId: Int, baseMap: Map<String, Double>): Flow<List<PriceItemDisplay>> {
        // Use the UseCase to get the data
        return getCustomerPricesUseCase(customerId) // UseCase call
            .map { customerItems ->
                customerItems.map { item ->
                    PriceItemDisplay(
                        id = item.id, workName = item.workName, price = item.price,
                        isBaseItem = false, customerId = item.customerId,
                        basePriceForReference = baseMap[item.workName] // Look up base price
                    )
                }
            }
    }

    // --- Update and Save Logic ---

    fun updatePriceItem(position: Int, newPrice: Double) {
        // This logic remains internal to the ViewModel
        val currentList = _editablePriceItems.value.toMutableList()
        if (position >= 0 && position < currentList.size) {
            val item = currentList[position]
            if (item.price != newPrice && newPrice >= 0) {
                currentList[position] = item.copy(price = newPrice)
                _editablePriceItems.value = currentList
                Log.d("ViewModel", "Updated item at $position with price $newPrice")
            }
        } else {
            Log.w("ViewModel", "updatePriceItem called with invalid position: $position")
        }
    }

    fun saveCurrentPriceList() {
        val itemsToSave = _editablePriceItems.value
        if (itemsToSave.isEmpty() && _priceMode.value == PriceMode.Base) {
            Log.w("ViewModel", "Attempted to save empty Base Price list. Aborting.")
            return
        }

        viewModelScope.launch {
            try {
                when (val mode = _priceMode.value) {
                    is PriceMode.Base -> {
                        val baseItems = itemsToSave.map {
                            BasePriceItem(id = it.id, workName = it.workName, price = it.price)
                        }
                        // Use the Save UseCase
                        saveBasePricesUseCase(baseItems) // UseCase call
                        Log.i("ViewModel", "Saved ${baseItems.size} base price items.")
                        // TODO: Add success feedback
                    }
                    is PriceMode.Customer -> {
                        val customerItems = itemsToSave.mapNotNull { // Keep filter for safety
                            if(it.customerId == mode.customerId) {
                                CustomerPriceItem(id = it.id, customerId = mode.customerId, workName = it.workName, price = it.price)
                            } else {
                                Log.e("ViewModel", "Data inconsistency: Item ${it.workName} has wrong customerId (${it.customerId}) for mode customer ${mode.customerId}")
                                null
                            }
                        }
                        if (itemsToSave.size != customerItems.size) {
                            Log.w("ViewModel", "Some items were skipped during customer price save due to inconsistency.")
                        }
                        // Use the Save UseCase
                        saveCustomerPricesUseCase(mode.customerId, customerItems) // UseCase call
                        Log.i("ViewModel", "Saved ${customerItems.size} price items for customer ${mode.customerId}.")
                        // TODO: Add success feedback
                    }
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Error saving price list", e)
                // TODO: Add error feedback
            }
        }
    }
}
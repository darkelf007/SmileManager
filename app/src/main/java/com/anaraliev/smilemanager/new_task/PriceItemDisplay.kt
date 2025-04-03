package com.anaraliev.smilemanager.new_task

data class PriceItemDisplay(
    val id: Long,                   // Original DB ID
    val workName: String,
    var price: Double,              // The price being displayed/edited (base or customer)
    val isBaseItem: Boolean,
    val customerId: Int? = null,
    val basePriceForReference: Double? = null // The corresponding base price (can be null if none exists)
)
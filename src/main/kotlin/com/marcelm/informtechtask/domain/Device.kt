package com.marcelm.informtechtask.domain

data class Device(
    val id: Long? = null,
    var name: String,
    var serialNo: String? = null,
    var purchasePrice: Double,
    var status: DeviceStatus,
    var deviceClass: DeviceClass = DeviceClass.Other,
    var comment: String? = null,
) {
    // fun getTotalCost(): Double = purchasePrice + repairs.sumOf { it.cost() }
}

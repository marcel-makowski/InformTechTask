package com.marcelm.informtechtask.domain

enum class DeviceStatus {
    Working,
    Broken,
    Incomplete,
    InRepair,
    PutOnSale,
    Unknown, ;

    fun string(): String = this.name

    companion object {
        fun fromString(value: String): DeviceStatus = DeviceStatus.entries.firstOrNull { it.name == value } ?: Unknown
    }
}

package com.marcelm.informtechtask.domain

enum class DeviceClass {
    Gameboy,
    DS,
    Switch,
    PSP,
    PSVita,
    Other, ;

    fun string(): String = this.name

    companion object {
        fun fromString(value: String): DeviceClass = DeviceClass.entries.firstOrNull { it.name == value } ?: Other
    }
}

package com.marcelm.informtechtask.application.port.persistence

import com.marcelm.informtechtask.domain.Device

interface WriteDevicePort {
    fun saveNew(device: Device): Device

    fun update(device: Device): Device?

    fun deleteById(deviceId: Long)
}

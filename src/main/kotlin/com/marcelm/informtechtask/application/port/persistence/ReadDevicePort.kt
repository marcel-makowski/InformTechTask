package com.marcelm.informtechtask.application.port.persistence

import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.domain.Repair
import java.util.Optional

interface ReadDevicePort {
    fun existsDeviceByName(name: String): Boolean

    fun existsDeviceById(id: Long): Boolean

    fun fetchById(id: Long): Optional<Device>

    fun fetchAll(): List<Device>

    fun fetchRepairs(device: Device): List<Repair>
}

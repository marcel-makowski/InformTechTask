package com.marcelm.informtechtask.adapter.persistence

import com.marcelm.informtechtask.application.port.persistence.WriteDevicePort
import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.infrastructure.repository.DeviceRepository
import org.springframework.stereotype.Component

@Component
class WriteDeviceAdapter(
    private val deviceRepository: DeviceRepository,
    private val deviceJpaMapper: DeviceJpaMapper,
) : WriteDevicePort {
    override fun saveNew(device: Device): Device {
        val deviceData = deviceJpaMapper.toJpaEntity(device)
        val deviceSaved = deviceRepository.save(deviceData)
        return deviceJpaMapper.toDomain(deviceSaved)
    }

    override fun update(device: Device): Device? {
        val deviceId = device.id!!
        return deviceRepository
            .findById(deviceId)
            .map { deviceJpaMapper.toJpaEntity(device, it) }
            .map { deviceRepository.save(it) }
            .map { deviceJpaMapper.toDomain(it) }
            .orElse(null)
    }

    override fun deleteById(deviceId: Long) = deviceRepository.deleteById(deviceId)
}

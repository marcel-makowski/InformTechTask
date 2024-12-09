package com.marcelm.informtechtask.adapter.persistence

import com.marcelm.informtechtask.application.port.persistence.ReadDevicePort
import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.infrastructure.repository.DeviceRepository
import com.marcelm.informtechtask.infrastructure.repository.RepairRepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class ReadDeviceAdapter(
    private val deviceRepository: DeviceRepository,
    private val repairRepository: RepairRepository,
    private val deviceJpaMapper: DeviceJpaMapper,
    private val repairJpaMapper: RepairJpaMapper,
) : ReadDevicePort {
    override fun existsDeviceByName(name: String): Boolean = deviceRepository.findByName(name).isNotEmpty()

    override fun existsDeviceById(id: Long): Boolean = deviceRepository.findById(id).isPresent

    override fun fetchById(id: Long): Optional<Device> = deviceRepository.findById(id).map { deviceJpaMapper.toDomain(it) }

    override fun fetchAll(): List<Device> =
        deviceRepository
            .findAll()
            .map { deviceJpaMapper.toDomain(it) }

    override fun fetchRepairs(device: Device): List<Repair> =
        repairRepository.findByOwnerId(device.id!!).map { repairJpaMapper.toDomain(it) }
}

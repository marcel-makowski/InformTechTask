package com.marcelm.informtechtask.application.service

import com.marcelm.informtechtask.application.port.persistence.ReadDevicePort
import com.marcelm.informtechtask.application.port.persistence.WriteDevicePort
import com.marcelm.informtechtask.application.usecase.device.DeviceFindAllUseCase
import com.marcelm.informtechtask.application.usecase.device.DeviceFindByIdUseCase
import com.marcelm.informtechtask.application.usecase.device.SubmitDeviceCreateUseCase
import com.marcelm.informtechtask.application.usecase.device.SubmitDeviceDeleteUseCase
import com.marcelm.informtechtask.application.usecase.device.SubmitDeviceUpdateUseCase
import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class DeviceService(
    private val readDevicePort: ReadDevicePort,
    private val writeDevicePort: WriteDevicePort,
) : DeviceFindByIdUseCase,
    DeviceFindAllUseCase,
    SubmitDeviceCreateUseCase,
    SubmitDeviceDeleteUseCase,
    SubmitDeviceUpdateUseCase {
    override fun findById(id: Long): Device {
        val res = readDevicePort.fetchById(id)
        if (res.isEmpty) throw EntityNotFoundException()
        return res.get()
    }

    override fun findAllDevices(): Collection<Device> = readDevicePort.fetchAll()

    override fun saveDevice(device: Device): Outcome.WithResult<Device, ErrorEnums.PersistenceItemErrors> {
        require(!readDevicePort.existsDeviceByName(device.name)) {
            return Outcome.WithResult.Error(ErrorEnums.PersistenceItemErrors.NameAlreadyExists, "Device already exists: ${device.name}")
        }
        return Outcome.WithResult.Ok(writeDevicePort.saveNew(device))
    }

    override fun updateDevice(device: Device): Device = writeDevicePort.update(device) ?: throw EntityNotFoundException()

    override fun deleteDeviceById(id: Long) = writeDevicePort.deleteById(id)
}

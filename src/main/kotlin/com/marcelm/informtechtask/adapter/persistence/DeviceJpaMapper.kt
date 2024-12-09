package com.marcelm.informtechtask.adapter.persistence

import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.domain.DeviceClass
import com.marcelm.informtechtask.domain.DeviceStatus
import com.marcelm.informtechtask.infrastructure.repository.data.DevicePersistenceData
import org.springframework.stereotype.Component

@Component
class DeviceJpaMapper {
    fun toJpaEntity(device: Device) =
        DevicePersistenceData(
            device.id,
            device.name,
            device.serialNo,
            device.purchasePrice,
            device.status.string(),
            device.deviceClass
                .string(),
            device.comment,
        )

    fun toJpaEntity(
        device: Device,
        persistedDeviceData: DevicePersistenceData,
    ) = persistedDeviceData.copy(
        deviceId = device.id,
        name = device.name,
        serialNo = device.serialNo,
        purchasePrice = device.purchasePrice,
        status = device.status.string(),
        deviceClass =
            device.deviceClass
                .string(),
        comment = device.comment,
    )

    fun toDomain(deviceData: DevicePersistenceData) =
        Device(
            deviceData.deviceId,
            deviceData.name,
            deviceData.serialNo,
            deviceData.purchasePrice,
            DeviceStatus.fromString(deviceData.status),
            DeviceClass.fromString(deviceData.deviceClass),
            deviceData.comment,
        )
}

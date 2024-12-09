package com.marcelm.informtechtask.adapter.api

import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.domain.DeviceClass
import com.marcelm.informtechtask.domain.DeviceStatus
import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeDeviceDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseDeviceDto
import org.springframework.stereotype.Component

@Component
class DeviceDtoMapper {
    fun toResponseDto(device: Device): ResponseDeviceDto =
        ResponseDeviceDto
            .Builder(
                id = device.id,
                name = device.name,
                status = device.status.string(),
                deviceClass = device.deviceClass.string(),
            ).comment(device.comment)
            .purchasePrice(device.purchasePrice)
            .serialNo(device.serialNo)
            .build()

    fun fromRequestDto(dto: RequestChangeDeviceDto): Device = fromRequestDtoWithId(null, dto)

    fun fromRequestDtoWithId(
        id: Long?,
        dto: RequestChangeDeviceDto,
    ): Device =
        Device(
            id = id,
            name = dto.name,
            serialNo = dto.serialNo,
            purchasePrice = dto.purchasePrice,
            status = DeviceStatus.fromString(dto.status),
            deviceClass = DeviceClass.fromString(dto.deviceClass),
            comment = dto.comment,
        )
}

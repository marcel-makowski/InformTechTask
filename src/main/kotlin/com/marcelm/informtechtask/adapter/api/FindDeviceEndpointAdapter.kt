package com.marcelm.informtechtask.adapter.api

import com.marcelm.informtechtask.application.port.entrypoint.FindDeviceEndpointPort
import com.marcelm.informtechtask.application.usecase.device.DeviceFindAllUseCase
import com.marcelm.informtechtask.application.usecase.device.DeviceFindByIdUseCase
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseDeviceDto
import org.springframework.stereotype.Component

@Component
class FindDeviceEndpointAdapter(
    private val deviceFindAllUseCase: DeviceFindAllUseCase,
    private val deviceFindByIdUseCase: DeviceFindByIdUseCase,
    private val deviceDtoMapper: DeviceDtoMapper,
) : FindDeviceEndpointPort {
    override fun fetchAllDevices(): Collection<ResponseDeviceDto> =
        deviceFindAllUseCase.findAllDevices().map {
            deviceDtoMapper.toResponseDto(it)
        }

    override fun fetchDeviceById(id: Long): ResponseDeviceDto = deviceDtoMapper.toResponseDto(deviceFindByIdUseCase.findById(id))
}

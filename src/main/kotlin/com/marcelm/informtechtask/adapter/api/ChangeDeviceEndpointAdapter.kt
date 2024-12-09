package com.marcelm.informtechtask.adapter.api

import com.marcelm.informtechtask.application.port.entrypoint.ChangeDeviceEndpointPort
import com.marcelm.informtechtask.application.usecase.device.SubmitDeviceCreateUseCase
import com.marcelm.informtechtask.application.usecase.device.SubmitDeviceDeleteUseCase
import com.marcelm.informtechtask.application.usecase.device.SubmitDeviceUpdateUseCase
import com.marcelm.informtechtask.errorhandling.Outcome
import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeDeviceDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseDeviceDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ChangeDeviceEndpointAdapter(
    private val submitDeviceCreateUseCase: SubmitDeviceCreateUseCase,
    private val submitDeviceDeleteUseCase: SubmitDeviceDeleteUseCase,
    private val submitDeviceUpdateUseCase: SubmitDeviceUpdateUseCase,
    private val deviceDtoMapper: DeviceDtoMapper,
) : ChangeDeviceEndpointPort {
    override fun saveDevice(changeDeviceInfoDto: RequestChangeDeviceDto): ResponseEntity<out Any> {
        val device = deviceDtoMapper.fromRequestDto(changeDeviceInfoDto)
        val outcome = submitDeviceCreateUseCase.saveDevice(device)
        if (outcome is Outcome.WithResult.Error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome.detail)
        }
        return ResponseEntity.ok(deviceDtoMapper.toResponseDto(outcome.value()))
    }

    override fun updateDevice(
        id: Long,
        changeDeviceInfoDto: RequestChangeDeviceDto,
    ): ResponseDeviceDto {
        val device = deviceDtoMapper.fromRequestDtoWithId(id, changeDeviceInfoDto)
        val updatedDevice = submitDeviceUpdateUseCase.updateDevice(device)
        return deviceDtoMapper.toResponseDto(updatedDevice)
    }

    override fun deleteDevice(id: Long) {
        submitDeviceDeleteUseCase.deleteDeviceById(id)
    }


}

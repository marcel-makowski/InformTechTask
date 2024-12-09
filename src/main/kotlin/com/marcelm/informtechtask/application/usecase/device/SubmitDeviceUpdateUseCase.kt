package com.marcelm.informtechtask.application.usecase.device

import com.marcelm.informtechtask.domain.Device

interface SubmitDeviceUpdateUseCase {
    fun updateDevice(device: Device): Device
}

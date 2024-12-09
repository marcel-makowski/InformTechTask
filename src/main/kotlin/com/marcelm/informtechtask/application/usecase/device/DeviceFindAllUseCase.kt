package com.marcelm.informtechtask.application.usecase.device

import com.marcelm.informtechtask.domain.Device

interface DeviceFindAllUseCase {
    fun findAllDevices(): Collection<Device>
}

package com.marcelm.informtechtask.application.usecase.device

import com.marcelm.informtechtask.domain.Device

interface DeviceFindByIdUseCase {
    fun findById(id: Long): Device
}

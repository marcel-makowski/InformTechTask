package com.marcelm.informtechtask.application.usecase.device

import com.marcelm.informtechtask.domain.Device
import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome

interface SubmitDeviceCreateUseCase {
    fun saveDevice(device: Device): Outcome.WithResult<Device, ErrorEnums.PersistenceItemErrors>
}

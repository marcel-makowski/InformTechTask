package com.marcelm.informtechtask.infrastructure.controller.dtos

import java.time.LocalDateTime

data class RequestChangeRepairDto(
    val id: Long? = null,
    val ownerId: Long,
    val description: String,
    val cost: Double,
    val isInProgress: Boolean,
    val isSuccessful: Boolean,
    val timeElapsedSeconds: Long,
    val lastStartTime: LocalDateTime?,
)

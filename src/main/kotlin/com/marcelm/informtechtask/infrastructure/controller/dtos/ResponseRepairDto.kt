package com.marcelm.informtechtask.infrastructure.controller.dtos

import java.time.LocalDateTime

class ResponseRepairDto private constructor(
    var id: Long?,
    var ownerId: Long,
    val description: String,
    val cost: Double,
    val isInProgress: Boolean,
    val isSuccessful: Boolean,
    val timeElapsedSeconds: Long,
    val lastStartTime: LocalDateTime?,
) {
    data class Builder(
        private var id: Long?,
        private var ownerId: Long,
        private var description: String,
        private var cost: Double,
        private var isInProgress: Boolean,
        private var isSuccessful: Boolean,
        private var timeElapsedSeconds: Long,
        private var lastStartTime: LocalDateTime?,
    ) {
        fun id(id: Long) = apply { this.id = id }

        fun ownerId(ownerId: Long) = apply { this.ownerId = ownerId }

        fun description(description: String) = apply { this.description = description }

        fun cost(cost: Double) = apply { this.cost = cost }

        fun isInProgress(isInProgress: Boolean) = apply { this.isInProgress = isInProgress }

        fun isSuccessful(isSuccessful: Boolean) = apply { this.isSuccessful = isSuccessful }

        fun timeElapsedSeconds(timeElapsedSeconds: Long) = apply { this.timeElapsedSeconds = timeElapsedSeconds }

        fun lastStartTime(lastStartTime: LocalDateTime) = apply { this.lastStartTime = lastStartTime }

        fun build(): ResponseRepairDto =
            ResponseRepairDto(id, ownerId, description, cost, isInProgress, isSuccessful, timeElapsedSeconds, lastStartTime)
    }
}

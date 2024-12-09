package com.marcelm.informtechtask.domain

import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome
import java.time.Duration
import java.time.LocalDateTime

class Repair(
    val id: Long?,
    val ownerId: Long,
    var description: String,
    var cost: Double,
    var lastStartTime: LocalDateTime? = null,
    var timeElapsedSeconds: Long = 0,
    var isSuccessful: Boolean,
    var isInProgress: Boolean,
) {
    fun stop(wasSuccessful: Boolean): Outcome.WithError<ErrorEnums.RepairErrors> {
        if (!isInProgress) return Outcome.WithError.Error(ErrorEnums.RepairErrors.RepairNotInProgress, "Repair is not started")
        isInProgress = false
        isSuccessful = wasSuccessful
        timeElapsedSeconds += Duration.between(lastStartTime!!, LocalDateTime.now()).seconds

        return Outcome.WithError.Ok()
    }

    fun start(): Outcome.WithError<ErrorEnums.RepairErrors> {
        if (isInProgress) return Outcome.WithError.Error(ErrorEnums.RepairErrors.RepairAlreadyInProgress, "Repair already in progress")
        if (isSuccessful) return Outcome.WithError.Error(ErrorEnums.RepairErrors.RepairAlreadyFinished, "Repair already finished")
        lastStartTime = LocalDateTime.now()
        isInProgress = true
        return Outcome.WithError.Ok()
    }
}

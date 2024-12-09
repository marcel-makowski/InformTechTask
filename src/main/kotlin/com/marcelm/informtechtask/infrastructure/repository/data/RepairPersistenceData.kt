package com.marcelm.informtechtask.infrastructure.repository.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "repair")
data class RepairPersistenceData(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val repairId: Long? = null,
    @Column
    val ownerId: Long = 0,
    @Column
    val description: String = "",
    @Column
    val cost: Double = 0.0,
    @Column
    var lastStartTime: LocalDateTime? = null,
    @Column
    var timeElapsedSeconds: Long = 0,
    @Column
    var successful: Boolean = false,
    @Column
    var inProgress: Boolean = false,
    @Column
    var comment: String = "",
)

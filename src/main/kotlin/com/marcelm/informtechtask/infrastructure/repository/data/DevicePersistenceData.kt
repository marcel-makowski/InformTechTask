package com.marcelm.informtechtask.infrastructure.repository.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "device")
data class DevicePersistenceData(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val deviceId: Long? = null,
    @Column(unique = true, nullable = false)
    var name: String = "",
    @Column(name = "serial_number", unique = true, nullable = true)
    var serialNo: String? = null,
    @Column(name = "purchase_price", nullable = false)
    var purchasePrice: Double = 0.0,
    @Column(nullable = false)
    var status: String = "",
    @Column(nullable = false)
    var deviceClass: String = "",
    @Column(nullable = true)
    var comment: String? = null,
)

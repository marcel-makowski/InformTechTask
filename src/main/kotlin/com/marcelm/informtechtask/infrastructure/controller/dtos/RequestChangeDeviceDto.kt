package com.marcelm.informtechtask.infrastructure.controller.dtos

data class RequestChangeDeviceDto(
    val id: Long? = null,
    val name: String,
    var serialNo: String? = null,
    var purchasePrice: Double,
    var status: String,
    var deviceClass: String,
    var comment: String? = null,
)

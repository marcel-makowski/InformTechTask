package com.marcelm.informtechtask.infrastructure.controller.dtos

class ResponseDeviceDto private constructor(
    var id: Long?,
    val name: String,
    val serialNo: String? = null,
    val purchasePrice: Double,
    val status: String,
    val deviceClass: String,
    val comment: String? = null,
) {
    data class Builder(
        var id: Long?,
        var name: String,
        var serialNo: String? = null,
        var purchasePrice: Double = 0.0,
        var status: String,
        var deviceClass: String,
        var comment: String? = null,
    ) {
        fun id(id: Long) = apply { this.id = id }

        fun name(name: String) = apply { this.name = name }

        fun serialNo(serialNo: String?) = apply { this.serialNo = serialNo }

        fun purchasePrice(purchasePrice: Double) = apply { this.purchasePrice = purchasePrice }

        fun status(status: String) = apply { this.status = status }

        fun deviceClass(deviceClass: String) = apply { this.deviceClass = deviceClass }

        fun comment(comment: String?) = apply { this.comment = comment }

        fun build(): ResponseDeviceDto = ResponseDeviceDto(id, name, serialNo, purchasePrice, status, deviceClass, comment)
    }
}

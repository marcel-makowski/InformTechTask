package com.marcelm.informtechtask.errorhandling

class Outcome {
    sealed class Simple {
        data object Ok : Simple()

        data object Error : Simple()
    }

    sealed class WithError<ErrorCode : Enum<ErrorCode>> {
        class Ok<ErrorCode : Enum<ErrorCode>> : WithError<ErrorCode>()

        data class Error<ErrorCode : Enum<ErrorCode>>(
            val code: ErrorCode,
            val detail: Any? = code.toString(),
        ) : WithError<ErrorCode>()
    }

    sealed class WithResult<SuccessResult, ErrorCode : Enum<ErrorCode>> {
        data class Ok<Result, ErrorCode : Enum<ErrorCode>>(
            private val result: Result,
        ) : WithResult<Result, ErrorCode>() {
            override fun value() = result
        }

        data class Error<SuccessResult, ErrorCode : Enum<ErrorCode>>(
            val code: ErrorCode,
            val detail: String = code.toString(),
        ) : WithResult<SuccessResult, ErrorCode>() {
            override fun value(): SuccessResult = throw Error("Error code: $code - $detail")
        }

        abstract fun value(): SuccessResult
    }
}

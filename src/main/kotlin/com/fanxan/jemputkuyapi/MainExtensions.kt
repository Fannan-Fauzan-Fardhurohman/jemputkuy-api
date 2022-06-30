package com.fanxan.jemputkuyapi

inline fun <reified T> T?.orThrow(
    message: String = "${T::class.simpleName} is null"
): T {
    return this ?: throw JemputkuyException(message)
}

inline fun <reified T> T?.toResult(
    message: String = "${T::class.simpleName} is null"
): Result<T> {
    return if (this != null) {
        Result.success(this)
    } else {
        Result.failure(JemputkuyException(message))
    }
}

fun <T> Result<T>.toResponse(): BaseResponse<T> {
    return if (this.isFailure) {
//        BaseResponse.failure(message)
        throw JemputkuyException(this.exceptionOrNull()?.message ?: "Failure")
    } else {
        BaseResponse.success(this.getOrNull())
    }
}
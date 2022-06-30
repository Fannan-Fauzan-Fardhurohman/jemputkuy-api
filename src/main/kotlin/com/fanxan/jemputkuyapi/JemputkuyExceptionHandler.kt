package com.fanxan.jemputkuyapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class JemputkuyExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [JemputkuyException::class])
    fun handlerThrowable(throwable: JemputkuyException): ResponseEntity<BaseResponse<Empty>> {
        return ResponseEntity(BaseResponse.failure(throwable.message?: "Failure"), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
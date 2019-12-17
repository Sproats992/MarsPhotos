package com.robertsproats.marsrobots.repository.services

import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {

    companion object {
        const val SOCKET_TIMEOUT_ERROR_CODE = 42
        const val HTTP_UNAUTHORISED = 401
        const val HTTP_NOT_FOUND = 404
    }

    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(getErrorMessage(SOCKET_TIMEOUT_ERROR_CODE), null)
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            SOCKET_TIMEOUT_ERROR_CODE -> "Timeout"
            HTTP_UNAUTHORISED -> "Unauthorised"
            HTTP_NOT_FOUND -> "Not found"
            else -> "Something went wrong"
        }
    }
}
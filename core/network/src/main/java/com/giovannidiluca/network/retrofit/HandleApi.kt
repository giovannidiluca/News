package com.giovannidiluca.network.retrofit

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(execute: suspend () -> Response<T>): NewsResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiSuccess(body)
        } else {
            ApiFailure(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiFailure(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiException(e)
    }
}

sealed interface NewsResult<T : Any>
class ApiSuccess<T : Any>(val data: T) :
    NewsResult<T>

class ApiFailure<T : Any>(val code: Int, val message: String?) :
    NewsResult<T>

class ApiException<T : Any>(val e: Throwable) :
    NewsResult<T>
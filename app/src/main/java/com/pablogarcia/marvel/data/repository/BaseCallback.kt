package com.pablogarcia.marvel.data.repository

import android.os.Bundle
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface BaseCallback<T> {

    fun onSuccess(httpCode: Int, data: T?)

    fun onError(httpCode: Int, bundle: Bundle)

    fun always()

}

class BaseCallBackContinuation<T>(
    private val continuation: Continuation<DataResult<T>>
) : BaseCallback<T> {

    override fun onSuccess(httpCode: Int, data: T?) = continuation.resume(
        DataResult(httpCode, data)
    )

    override fun onError(httpCode: Int, bundle: Bundle) = continuation.resumeWithException(
        DataException(httpCode, bundle)
    )

    override fun always() {}
}

data class DataException(
    var httpCode: Int,
    var bundle: Bundle
) : Exception()

data class DataResult<T>(
    var httpCode: Int,
    var value: T?
)

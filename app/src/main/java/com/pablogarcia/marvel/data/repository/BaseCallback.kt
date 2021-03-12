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

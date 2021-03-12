package com.pablogarcia.marvel.framework.retrofit

import android.os.Bundle
import com.pablogarcia.marvel.data.mapper.BaseMapper
import com.pablogarcia.marvel.data.repository.BaseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ERROR_KEY = "ERROR_KEY"

class CloudRepositoryCallback<T, R> : Callback<T> {

    var callback: BaseCallback<R>? = null
    var mapper: BaseMapper<T, R>? = null

    override fun onFailure(call: Call<T>, t: Throwable) {

        callback?.onError(0, Bundle().apply {

            putString(ERROR_KEY, t.message)
        })
        callback?.always()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {

        if (response.isSuccessful) {

            callback?.onSuccess(response.code(), mapper?.map(response.body()))
        } else {

            callback?.onError(response.code(), Bundle().apply {

                putString(ERROR_KEY, response.message())
            })
        }
        callback?.always()
    }
}

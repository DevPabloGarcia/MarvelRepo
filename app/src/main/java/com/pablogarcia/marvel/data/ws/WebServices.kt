package com.pablogarcia.marvel.data.ws

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_TIMEOUT_SECS = 30

const val URL_BACKEND = "https://gateway.marvel.com/v1/public/"

private const val API_KEY = "cdc99b52e39733a0f343dbc7b52261aa"

private const val HASH = "d2d584b9a2b8c1261b3f0940ed805d3d"

private const val TIMESTAMP = "1"

private const val HEADER_TS = "ts"

private const val HEADER_API_KEY = "apikey"

private const val HEADER_HASH = "hash"

object WebServices {

    /**
     * Create service adding url end point and header params
     *
     * @param urlEndpoint
     * @param serviceClass
     */
    fun <S> createService(serviceClass: Class<S>): S {

        val httpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        httpClient.addInterceptor { chain -> return@addInterceptor addHeaderParams(chain) }
        httpClient.connectTimeout(HTTP_TIMEOUT_SECS.toLong(), TimeUnit.SECONDS)
        httpClient.readTimeout(HTTP_TIMEOUT_SECS.toLong(), TimeUnit.SECONDS)

        val retrofit: Retrofit = builder
            .baseUrl(URL_BACKEND)
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    /**
     * Add header params to url
     *
     * @param chain
     * @return response
     */
    private fun addHeaderParams(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder().apply {
            addQueryParameter(HEADER_TS, TIMESTAMP)
            addQueryParameter(HEADER_API_KEY, API_KEY)
            addQueryParameter(HEADER_HASH, HASH)
        }.build()
        request.url(newUrl)

        return chain.proceed(request.build())
    }
}

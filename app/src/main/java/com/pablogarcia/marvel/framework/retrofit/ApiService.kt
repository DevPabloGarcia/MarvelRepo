package com.pablogarcia.marvel.framework.retrofit

import com.pablogarcia.marvel.data.responses.CharactersResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    fun getCharacters(@Query("offset") offset: Int?): Call<CharactersResponse>
}

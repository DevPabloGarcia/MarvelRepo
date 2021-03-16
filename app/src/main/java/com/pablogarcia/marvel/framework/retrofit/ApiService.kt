package com.pablogarcia.marvel.framework.retrofit

import com.pablogarcia.marvel.data.responses.CharactersResponse
import com.pablogarcia.marvel.data.responses.ComicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    fun getCharacters(@Query("offset") offset: Int?): Call<CharactersResponse>

    @GET("characters/{character_id}/comics")
    fun getComics(@Path("character_id") characterId: String?): Call<ComicsResponse>
}

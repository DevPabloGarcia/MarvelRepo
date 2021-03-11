package com.pablogarcia.marvel.data.ws

import com.pablogarcia.marvel.data.responses.CharacterResponse
import com.pablogarcia.marvel.data.responses.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface WebServiceInterface {

    @GET("characters")
    fun getCharacters(@Query("offset") offset: Int?): Call<CharactersResponse>

    @GET
    fun getCharacter(@Url url: String): Call<CharacterResponse>
}

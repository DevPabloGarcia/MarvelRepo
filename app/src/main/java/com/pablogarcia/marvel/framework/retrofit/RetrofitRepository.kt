package com.pablogarcia.marvel.framework.retrofit

import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.data.repository.cloud.CloudRepositoryInterface
import com.pablogarcia.marvel.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RetrofitRepository : CloudRepositoryInterface {

    override suspend fun getCharacters(offset: Int) : Result<List<Character>> {

        val client: ApiService = RetrofitServiceInterface.createService(ApiService::class.java)
        val characters = withContext(Dispatchers.IO) { client.getCharacters(offset).execute() }
        val mapper = CharactersMapper()
        return if (characters.isSuccessful) {
            Result.Success(mapper.map(characters.body()))
        } else {
            Result.Error(Exception("Something wrong!"))
        }
    }
}

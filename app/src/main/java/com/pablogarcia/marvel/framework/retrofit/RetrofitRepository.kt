package com.pablogarcia.marvel.framework.retrofit

import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.data.repository.cloud.CloudRepository
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RetrofitRepository : CloudRepository {

    override suspend fun getCharacters(offset: Int, mapper: CharactersMapper): Result<List<Character>> {

        val client: ApiService = RetrofitServiceInterface.createService(ApiService::class.java)
        val characters = withContext(Dispatchers.IO) { client.getCharacters(offset).execute() }
        return if (characters.isSuccessful) {
            Result.Success(mapper.map(characters.body()))
        } else {
            Result.Error(Exception("Something wrong!"))
        }
    }

    override suspend fun getComics(characterId: String, mapper: ComicsMapper): Result<List<Comic>> {
        val client: ApiService = RetrofitServiceInterface.createService(ApiService::class.java)
        val comics = withContext(Dispatchers.IO) { client.getComics(characterId).execute() }
        return if (comics.isSuccessful) {
            Result.Success(mapper.map(comics.body()))
        } else {
            Result.Error(Exception("Something wrong!"))
        }
    }
}

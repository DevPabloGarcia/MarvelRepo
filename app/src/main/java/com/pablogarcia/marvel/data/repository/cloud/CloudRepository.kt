package com.pablogarcia.marvel.data.repository.cloud

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic
import javax.inject.Inject


class CloudRepository @Inject constructor(
    private var cloudDatabase: CloudRepositoryInterface
) {

    suspend fun getCharacters(offset: Int): Result<List<Character>> {
        return cloudDatabase.getCharacters(offset)
    }

    suspend fun getComics(characterId: String): Result<List<Comic>> {
        return cloudDatabase.getComics(characterId)
    }
}

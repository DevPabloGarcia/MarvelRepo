package com.pablogarcia.marvel.data.repository.cloud

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic

interface CloudRepositoryInterface {

    suspend fun getCharacters(offset: Int) : Result<List<Character>>

    suspend fun getComics(characterId: String) : Result<List<Comic>>
}

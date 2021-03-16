package com.pablogarcia.marvel.data.repository.cloud

import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic

interface CloudRepository {

    suspend fun getCharacters(offset: Int, mapper: CharactersMapper) : Result<List<Character>>

    suspend fun getComics(characterId: String, mapper: ComicsMapper) : Result<List<Comic>>
}

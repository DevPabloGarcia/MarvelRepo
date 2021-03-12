package com.pablogarcia.marvel.data.repository.cloud

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character

interface CloudRepositoryInterface {

    suspend fun getCharacters(offset: Int) : Result<List<Character>>
}

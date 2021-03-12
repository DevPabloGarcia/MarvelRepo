package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.model.Character

interface DataRepository {

    /**
     * Obtain all characters
     */
    suspend fun getCharacters(fromLocal: Boolean) : Result<List<Character>>
}

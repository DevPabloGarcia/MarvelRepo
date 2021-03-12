package com.pablogarcia.marvel.data.repository.local

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character

interface LocalRepositoryInterface {

    /**
     * Insert a list of characters
     *
     * @param characters - character list
     */
    suspend fun insertAll(characters: List<Character>)

    /**
     * Obtain a list of characters
     *
     * @return list of characters
     */
    suspend fun getCharacters() : Result<List<Character>>

    suspend fun getNumberOfCharacters() : Result<Int>
}

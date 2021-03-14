package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic

interface DataRepository {

    /**
     * Obtain all characters
     *
     * @param fromLocal - true if must get data from local database
     * @return list of characters
     */
    suspend fun getCharacters(fromLocal: Boolean): Result<List<Character>>

    /**
     * Obtain comics where character appears
     *
     * @param characterId
     */
    suspend fun getComics(characterId: String): Result<List<Comic>>

    /**
     * Update character
     *
     * @param character - new character
     */
    suspend fun updateCharacter(character: Character)
}

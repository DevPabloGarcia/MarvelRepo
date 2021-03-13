package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.model.Character

interface DataRepository {

    /**
     * Obtain all characters
     *
     * @param fromLocal - true if must get data from local database
     * @param favorites - true if must get favorites characters
     * @return list of characters
     */
    suspend fun getCharacters(fromLocal: Boolean): Result<List<Character>>

    /**
     * Update character
     *
     * @param character - new character
     */
    suspend fun updateCharacter(character: Character)
}

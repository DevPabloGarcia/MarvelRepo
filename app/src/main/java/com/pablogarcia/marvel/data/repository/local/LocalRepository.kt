package com.pablogarcia.marvel.data.repository.local

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.framework.room.mapper.RoomToCharacterMapper
import com.pablogarcia.marvel.model.Character

interface LocalRepository {

    /**
     * Insert a list of characters
     *
     * @param characters - character list
     */
    suspend fun insertAll(characters: List<Character>, mapper: CharacterToRoomMapper)

    /**
     * Obtain a list of characters
     *
     * @return list of characters
     */
    suspend fun getCharacters(mapper: RoomToCharacterMapper): Result<List<Character>>

    /**
     * Obtain a list of characters
     *
     * @return list of characters
     */
    suspend fun getFavoriteCharacters(mapper: RoomToCharacterMapper): Result<List<Character>>

    /**
     * Count characters
     *
     * @return number of characters
     */
    suspend fun getNumberOfCharacters(): Result<Int>

    /**
     * Update character with new data
     *
     * @param character - new character
     */
    suspend fun updateCharacter(character: Character, mapper: CharacterToRoomMapper)
}

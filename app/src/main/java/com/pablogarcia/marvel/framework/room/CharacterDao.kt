package com.pablogarcia.marvel.framework.room

import androidx.room.*


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<Character>)

    @Update
    fun update(character: Character)

    @Delete
    fun delete(character: Character)

    @Query("SELECT * FROM " + Character.TABLE_NAME + " WHERE Character.id=:characterId")
    fun getCharacter(characterId: Int): Character

    @Query("SELECT * FROM " + Character.TABLE_NAME + " ORDER BY name")
    fun getCharacters(): List<Character>

    @Query("SELECT * FROM " + Character.TABLE_NAME + " WHERE Character.like = 1 ORDER BY name")
    fun getFavoriteCharacters(): List<Character>

    @Query("SELECT COUNT(*) FROM " + Character.TABLE_NAME)
    fun getCharacterCount(): Int?
}

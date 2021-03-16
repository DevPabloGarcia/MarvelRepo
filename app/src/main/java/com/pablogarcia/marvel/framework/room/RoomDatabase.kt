package com.pablogarcia.marvel.framework.room

import android.app.Application
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.framework.room.mapper.RoomToCharacterMapper
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.data.repository.local.LocalRepository
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class RoomDatabase @Inject constructor(application: Application) : LocalRepository {

    private val characterDao: CharacterDao? =
        CharactersDataBase.getInstance(application)?.characterDao()

    override suspend fun insertAll(characters: List<Character>, mapper: CharacterToRoomMapper) {

        characterDao?.insertAll(mapper.mapAll(characters))
    }

    override suspend fun getCharacters(mapper: RoomToCharacterMapper) : Result<List<Character>> {

        val characters = mutableListOf<Character>()
        characterDao?.getCharacters()?.forEach { _character ->
            characters.add(mapper.map(_character))
        }
        return Result.Success(characters)
    }

    override suspend fun getFavoriteCharacters(mapper: RoomToCharacterMapper): Result<List<Character>> {
        val characters = mutableListOf<Character>()
        characterDao?.getFavoriteCharacters()?.forEach { _character ->
            characters.add(mapper.map(_character))
        }
        return Result.Success(characters)
    }

    override suspend fun getNumberOfCharacters(): Result<Int> {

        val rowCount = characterDao?.getCharacterCount()
        return Result.Success(rowCount ?: 0)
    }

    override suspend fun updateCharacter(character: Character, mapper: CharacterToRoomMapper) {
        characterDao?.update(mapper.map(character))
    }
}

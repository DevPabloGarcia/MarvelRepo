package com.pablogarcia.marvel.framework.room

import android.app.Application
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.framework.room.mapper.RoomToCharacterMapper
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.data.repository.local.LocalRepositoryInterface
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class RoomDatabase @Inject constructor(application: Application,
                                       private var characterToRoomMapper: CharacterToRoomMapper,
                                       private var roomToCharacterMapper: RoomToCharacterMapper
) : LocalRepositoryInterface {

    private val characterDao: CharacterDao? =
        CharactersDataBase.getInstance(application)?.characterDao()

    override suspend fun insertAll(characters: List<Character>) {

        characterDao?.insertAll(characterToRoomMapper.mapAll(characters))
    }

    override suspend fun getCharacters() : Result<List<Character>> {

        val characters = mutableListOf<Character>()
        characterDao?.getCharacters()?.forEach { _character ->
            characters.add(roomToCharacterMapper.map(_character))
        }
        return Result.Success(characters)
    }

    override suspend fun getFavoriteCharacters(): Result<List<Character>> {
        val characters = mutableListOf<Character>()
        characterDao?.getFavoriteCharacters()?.forEach { _character ->
            characters.add(roomToCharacterMapper.map(_character))
        }
        return Result.Success(characters)
    }

    override suspend fun getNumberOfCharacters(): Result<Int> {

        val rowCount = characterDao?.getCharacterCount()
        return Result.Success(rowCount ?: 0)
    }

    override suspend fun updateCharacter(character: Character) {
        characterDao?.update(character = characterToRoomMapper.map(character))
    }
}

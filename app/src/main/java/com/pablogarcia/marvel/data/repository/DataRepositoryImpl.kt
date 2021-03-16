package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.data.repository.cloud.CloudRepository
import com.pablogarcia.marvel.data.repository.local.LocalRepository
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.framework.room.mapper.RoomToCharacterMapper
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private var cloudRepository: CloudRepository,
    private var localRepository: LocalRepository
) : DataRepository {

    override suspend fun getCharacters(fromLocal: Boolean,
                                       characterMapper: CharactersMapper,
                                       roomMapper: RoomToCharacterMapper,
                                       characterRoomMapper: CharacterToRoomMapper
    ) : Result<List<Character>> {

        return if (fromLocal) {
            val result = localRepository.getCharacters(roomMapper)
            if (result is Result.Success && result.data.isNullOrEmpty())
                loadFromCloud(characterMapper, characterRoomMapper)
            else
                result

        } else {
            loadFromCloud(characterMapper, characterRoomMapper)
        }
    }

    override suspend fun getComics(characterId: String,
                                   mapper: ComicsMapper
    ): Result<List<Comic>> {

        return cloudRepository.getComics(characterId, mapper)
    }

    override suspend fun updateCharacter(character: Character, mapper: CharacterToRoomMapper) {

        localRepository.updateCharacter(character, mapper)
    }

    /**
     * Obtain characters from cloud and insert them in local database
     */
    private suspend fun loadFromCloud(characterMapper: CharactersMapper,
                                      characterToRoomMapper: CharacterToRoomMapper
    ): Result<List<Character>> {

        var offset = 0
        val charactersCount = localRepository.getNumberOfCharacters()
        if (charactersCount is Result.Success) {
            offset = charactersCount.data
        }
        val characters = cloudRepository.getCharacters(offset, characterMapper)
        if (characters is Result.Success) {
            localRepository.insertAll(characters.data, characterToRoomMapper)
        }
        return characters
    }
}

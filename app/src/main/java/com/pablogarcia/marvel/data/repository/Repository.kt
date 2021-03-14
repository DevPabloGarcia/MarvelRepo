package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.data.repository.cloud.CloudRepository
import com.pablogarcia.marvel.data.repository.local.LocalRepository
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic
import javax.inject.Inject

class Repository @Inject constructor(
    private var cloudRepository: CloudRepository,
    private var localRepository: LocalRepository
) : DataRepository {

    override suspend fun getCharacters(fromLocal: Boolean) : Result<List<Character>> {

        return if (fromLocal) {
            val result = localRepository.getCharacters()
            if (result is Result.Success && result.data.isNullOrEmpty())
                loadFromCloud()
            else
                result

        } else {
            loadFromCloud()
        }
    }

    override suspend fun getComics(characterId: String): Result<List<Comic>> {
        return cloudRepository.getComics(characterId)
    }

    override suspend fun updateCharacter(character: Character) {

        localRepository.updateCharacter(character)
    }

    /**
     * Obtain characters from cloud and insert them in local database
     */
    private suspend fun loadFromCloud(): Result<List<Character>> {

        var offset = 0
        val charactersCount = localRepository.getCharactersCount()
        if (charactersCount is Result.Success) {
            offset = charactersCount.data
        }
        val characters = cloudRepository.getCharacters(offset)
        if (characters is Result.Success) {
            localRepository.insertAll(characters.data)
        }
        return characters
    }
}

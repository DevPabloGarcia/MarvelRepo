package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.data.repository.cloud.CloudRepository
import com.pablogarcia.marvel.data.repository.local.LocalRepository
import com.pablogarcia.marvel.model.Character

class Repository(
    var cloudRepository: CloudRepository,
    var localRepository: LocalRepository
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

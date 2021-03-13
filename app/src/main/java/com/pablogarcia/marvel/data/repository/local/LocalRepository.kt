package com.pablogarcia.marvel.data.repository.local

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class LocalRepository @Inject constructor(var localDatabase: LocalRepositoryInterface) {

    suspend fun insertAll(characters: List<Character>) {

        localDatabase.insertAll(characters)
    }

    suspend fun getCharacters(): Result<List<Character>> {

        return localDatabase.getCharacters()
    }

    suspend fun getCharactersCount(): Result<Int> {

        return localDatabase.getNumberOfCharacters()
    }

    suspend fun updateCharacter(character: Character) {

        localDatabase.updateCharacter(character)
    }
}

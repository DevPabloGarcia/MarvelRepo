package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.DataRepository
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

interface ObtainCharactersUseCase {

    suspend fun get(fromLocal: Boolean) : Result<List<Character>>
}

class ObtainCharactersUseCaseImpl @Inject constructor(
    repository: DataRepository
): BaseUseCase(repository), ObtainCharactersUseCase {

    override suspend fun get(fromLocal: Boolean) : Result<List<Character>> {
        return repository.getCharacters(fromLocal)
    }
}

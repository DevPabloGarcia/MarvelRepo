package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class ObtainCharactersUseCase @Inject constructor(): BaseUseCase() {

    suspend fun get(fromLocal: Boolean, favorite: Boolean) : Result<List<Character>> {

        return repository.getCharacters(fromLocal, favorite)
    }
}

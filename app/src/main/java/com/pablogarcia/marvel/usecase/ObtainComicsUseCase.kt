package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Comic
import javax.inject.Inject

class ObtainComicsUseCase @Inject constructor(): BaseUseCase() {

    suspend fun get(characterId: String) : Result<List<Comic>>{

        return repository.getComics(characterId)
    }
}

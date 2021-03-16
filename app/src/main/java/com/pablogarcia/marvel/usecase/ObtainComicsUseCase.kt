package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.data.repository.DataRepository
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Comic
import javax.inject.Inject

interface ObtainComicsUseCase {

    suspend fun get(characterId: String): Result<List<Comic>>
}

class ObtainComicsUseCaseImpl @Inject constructor(
    repository: DataRepository,
    var mapper: ComicsMapper
) : BaseUseCase(repository), ObtainComicsUseCase {

    override suspend fun get(characterId: String): Result<List<Comic>> {
        return repository.getComics(characterId, mapper)
    }
}

package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.DataRepository
import com.pablogarcia.marvel.framework.room.mapper.CharacterToRoomMapper
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

interface UpdateCharacterLikeUseCase {

    suspend fun post(character: Character)
}

class UpdateCharacterLikeUseCaseImpl @Inject constructor(
    repository: DataRepository,
    var mapper: CharacterToRoomMapper
) : BaseUseCase(repository), UpdateCharacterLikeUseCase {

    override suspend fun post(character: Character) {
        repository.updateCharacter(character, mapper)
    }
}

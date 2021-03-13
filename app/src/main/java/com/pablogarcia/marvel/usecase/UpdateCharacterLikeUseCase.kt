package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class UpdateCharacterLikeUseCase @Inject constructor(): BaseUseCase() {

    suspend fun post(character: Character) {

        repository.updateCharacter(character = character)
    }
}

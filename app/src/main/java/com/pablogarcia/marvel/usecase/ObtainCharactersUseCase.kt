package com.pablogarcia.marvel.usecase

import com.pablogarcia.marvel.data.repository.BaseCallBackContinuation
import com.pablogarcia.marvel.data.repository.DataResult
import com.pablogarcia.marvel.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

class ObtainCharactersUseCase @Inject constructor(): BaseUseCase() {

    suspend fun get() = withContext(Dispatchers.IO) {

        suspendCoroutine { continuation : Continuation<DataResult<List<Character>>> ->

            repository.getCharacters(BaseCallBackContinuation(continuation))
        }
    }
}

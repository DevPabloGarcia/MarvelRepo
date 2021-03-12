package com.pablogarcia.marvel.data.repository.cloud

import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject


class CloudRepository @Inject constructor(
    var cloudDatabase: CloudRepositoryInterface
) {

    suspend fun getCharacters(offset: Int) : Result<List<Character>>{

        return cloudDatabase.getCharacters(offset)
    }
}

package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.data.mapper.BaseMapper
import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.responses.CharactersResponse
import com.pablogarcia.marvel.data.ws.WebServiceInterface
import com.pablogarcia.marvel.data.ws.WebServices
import com.pablogarcia.marvel.model.Character
import retrofit2.Call


class CloudRepository : DataRepository {

    override fun getCharacters(callback: BaseCallback<List<Character>>) {

        val client: WebServiceInterface = WebServices.createService(WebServiceInterface::class.java)
        val call: Call<CharactersResponse> = client.getCharacters()
        enqueueCall(
            call,
            CharactersMapper(),
            callback,
            CloudRepositoryCallback()
        )
    }

    private fun <T, R> enqueueCall(call: Call<T>,
                                   mapper: BaseMapper<T, R>,
                                   domainCallback: BaseCallback<R>,
                                   callback: CloudRepositoryCallback<T, R>
    ) {

        callback.mapper = mapper
        callback.callback = domainCallback
        call.enqueue(callback)
    }
}

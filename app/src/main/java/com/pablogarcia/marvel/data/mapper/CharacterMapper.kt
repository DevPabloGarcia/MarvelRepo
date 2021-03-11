package com.pablogarcia.marvel.data.mapper

import com.pablogarcia.marvel.data.responses.CharacterResponse
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Thumbnail

object CharacterMapper: BaseMapper<CharacterResponse?, Character?>() {

    override fun map(response: CharacterResponse?): Character {

        return Character().apply {

            response?.let { _response ->

                id = _response.id
                name = response.name
                description = _response.description
                thumbnail = Thumbnail(_response.thumbnail?.path, _response.thumbnail?.extension)
            }
        }
    }
}

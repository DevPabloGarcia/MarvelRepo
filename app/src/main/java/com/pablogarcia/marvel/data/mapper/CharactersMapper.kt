package com.pablogarcia.marvel.data.mapper

import com.pablogarcia.marvel.data.responses.CharactersResponse
import com.pablogarcia.marvel.model.*
import javax.inject.Inject

class CharactersMapper @Inject constructor(): BaseMapper<CharactersResponse, List<Character>>() {

    override fun map(response: CharactersResponse?): List<Character> {

        val characterList = mutableListOf<Character>()
        response?.data?.results?.forEach {

            characterList.add(mapCharacter(it))
        }

        return characterList
    }

    private fun mapCharacter(data: CharactersResponse.CharacterResponse?): Character {

        return Character().apply {

            data?.let { _response ->

                id = _response.id
                name = data.name
                description = _response.description
                thumbnail = Thumbnail(_response.thumbnail?.path, _response.thumbnail?.extension)
            }
        }
    }
}

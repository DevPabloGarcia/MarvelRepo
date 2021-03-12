package com.pablogarcia.marvel.data.mapper

import com.pablogarcia.marvel.data.responses.CharacterResponse
import com.pablogarcia.marvel.data.responses.CharactersResponse
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Thumbnail

class CharactersMapper: BaseMapper<CharactersResponse, List<Character>>() {

    override fun map(data: CharactersResponse?): List<Character> {

        val characterList = mutableListOf<Character>()
        data?.data?.results?.forEach {

            characterList.add(mapCharacter(it))
        }

        return characterList
    }

    private fun mapCharacter(data: CharacterResponse?): Character {

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

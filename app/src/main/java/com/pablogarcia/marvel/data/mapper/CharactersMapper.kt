package com.pablogarcia.marvel.data.mapper

import com.pablogarcia.marvel.data.responses.CharactersResponse
import com.pablogarcia.marvel.model.Character

class CharactersMapper: BaseMapper<CharactersResponse, List<Character>>() {

    override fun map(response: CharactersResponse?): List<Character> {

        val characterList = mutableListOf<Character>()
        response?.data?.results?.forEach {

            characterList.add(CharacterMapper.map(it))
        }

        return characterList
    }
}

package com.pablogarcia.marvel.framework.room.mapper

import com.pablogarcia.marvel.data.mapper.BaseMapper
import com.pablogarcia.marvel.framework.room.Character
import com.pablogarcia.marvel.model.Thumbnail
import javax.inject.Inject

class RoomToCharacterMapper @Inject constructor(): BaseMapper<Character, com.pablogarcia.marvel.model.Character>() {

    override fun map(data: Character?): com.pablogarcia.marvel.model.Character {

        return com.pablogarcia.marvel.model.Character().apply {

            id = data?.id
            name = data?.name
            description = data?.description
            thumbnail = Thumbnail(data?.thumbnail?.path, data?.thumbnail?.extension)
            like = data?.like == 1
        }
    }

    fun mapAll(characters: List<Character>?) : List<com.pablogarcia.marvel.model.Character> {

        val list = mutableListOf<com.pablogarcia.marvel.model.Character>()
        characters?.forEach {

            list.add(map(it))
        }

        return list
    }
}

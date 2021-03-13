package com.pablogarcia.marvel.framework.room.mapper

import com.pablogarcia.marvel.data.mapper.BaseMapper
import com.pablogarcia.marvel.framework.room.Thumbnail
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class CharacterToRoomMapper @Inject constructor(): BaseMapper<Character, com.pablogarcia.marvel.framework.room.Character>() {

    override fun map(data: Character?): com.pablogarcia.marvel.framework.room.Character {

        return com.pablogarcia.marvel.framework.room.Character().apply {
            id = data?.id ?: 0
            name = data?.name
            description = data?.description
            thumbnail = Thumbnail(data?.thumbnail?.path, data?.thumbnail?.extension)
            like = if (data?.like == true) 1 else 0
        }
    }

    fun mapAll(characters: List<Character>?): List<com.pablogarcia.marvel.framework.room.Character> {

        val list = mutableListOf<com.pablogarcia.marvel.framework.room.Character>()
        characters?.forEach {
            list.add(map(it))
        }

        return list
    }
}

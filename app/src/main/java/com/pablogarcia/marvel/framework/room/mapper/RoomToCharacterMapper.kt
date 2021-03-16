package com.pablogarcia.marvel.framework.room.mapper

import com.pablogarcia.marvel.data.mapper.BaseMapper
import com.pablogarcia.marvel.framework.room.Character
import com.pablogarcia.marvel.model.Thumbnail
import javax.inject.Inject

class RoomToCharacterMapper @Inject constructor(): BaseMapper<Character, com.pablogarcia.marvel.model.Character>() {

    override fun map(response: Character?): com.pablogarcia.marvel.model.Character {

        return com.pablogarcia.marvel.model.Character().apply {

            id = response?.id
            name = response?.name
            description = response?.description
            thumbnail = Thumbnail(response?.thumbnail?.path, response?.thumbnail?.extension)
            like = response?.like == 1
        }
    }

}

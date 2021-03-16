package com.pablogarcia.marvel.data.mapper

import com.pablogarcia.marvel.data.responses.ComicsResponse
import com.pablogarcia.marvel.model.Comic
import com.pablogarcia.marvel.model.Thumbnail
import javax.inject.Inject

class ComicsMapper @Inject constructor() : BaseMapper<ComicsResponse, List<Comic>>() {

    override fun map(response: ComicsResponse?): List<Comic> {

        val comics: MutableList<Comic> = mutableListOf()
        response?.data?.results?.forEach { _response ->
            val comic = Comic(
                id = _response.id,
                title = _response.title,
                description = _response.description,
                pageCount = _response.pageCount,
                thumbnail = Thumbnail(_response.thumbnail?.path, _response.thumbnail?.extension)
            )
            comics.add(comic)
        }

        return comics
    }
}

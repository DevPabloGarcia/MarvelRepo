package com.pablogarcia.marvel.data.responses

class CharactersResponse {

    var data: DataResponse? = null

    inner class DataResponse {

        var results: MutableList<CharacterResponse>? = null
    }

    inner class CharacterResponse {

        var id: Int? = null
        var name: String? = null
        var description: String? = null
        var thumbnail: ThumbnailResponse? = null
    }
}

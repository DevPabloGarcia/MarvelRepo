package com.pablogarcia.marvel.data.responses

class CharactersResponse {

    var data: DataResponse? = null

    class DataResponse {

        var results: MutableList<CharacterResponse>? = null
    }

    class CharacterResponse {

        var id: Int? = null
        var name: String? = null
        var description: String? = null
        var thumbnail: ThumbnailResponse? = null
    }
}

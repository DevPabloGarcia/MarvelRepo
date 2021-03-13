package com.pablogarcia.marvel.data.responses

class CharactersResponse {

    var code: Int? = null
    var status: String? = null
    var copyright: String? = null
    var data: DataResponse? = null

    inner class DataResponse {

        var offset: Int? = null
        var limit: Int? = null
        var results: List<CharacterResponse>? = null
    }

    inner class CharacterResponse {

        var id: Int? = null
        var name: String? = null
        var description: String? = null
        var resourceURI: String? = null
        var thumbnail: ThumbnailResponse? = null
    }


}



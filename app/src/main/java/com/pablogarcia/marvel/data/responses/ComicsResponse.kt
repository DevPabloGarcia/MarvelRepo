package com.pablogarcia.marvel.data.responses

class ComicsResponse {

    var code: Int? = null
    var status: String? = null
    var copyright: String? = null
    var data: DataResponse? = null

    inner class DataResponse {

        var offset: Int? = null
        var limit: Int? = null
        var results: List<ComicResponse>? = null
    }

    inner class ComicResponse {

        var id: Int? = null
        var title: String? = null
        var description: String? = null
        var pageCount: Int? = null
        var thumbnail: ThumbnailResponse? = null
    }
}


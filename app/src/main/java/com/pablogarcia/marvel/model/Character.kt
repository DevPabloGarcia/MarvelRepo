package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Character(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var modified: Date? = null,
    var resourceURI: String? = null,
    var urls: List<Url>? = null,
    var thumbnail: Thumbnail? = null,
    var comics: ComicList? = null,
    var stories: StoryList? = null,
    var events: EventList? = null,
    var series: SeriesList? = null,
    var like: Boolean = false
) : Parcelable

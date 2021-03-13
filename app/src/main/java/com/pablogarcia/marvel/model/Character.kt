package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
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
) : Parcelable{

    fun obtainImage(imageType: ImageType): String {

        val imageSufix = when (imageType) {

            ImageType.LANDSCAPE_SMALL -> smallLandscapeSufix
            ImageType.LANDSCAPE_LARGE -> largeLandscapeSufix
            ImageType.PORTRAIT_SMALL -> smallPortraiteSufix
            ImageType.PORTRAIT_LARGE -> largePortraitSufix
        }

        var imagePrefix = thumbnail?.path
        if (thumbnail?.path?.indexOf("https") == -1)  {

            imagePrefix = thumbnail?.path?.replace("http", "https")
        }
        return imagePrefix + imageSufix + "." + thumbnail?.extension
    }

    companion object {

         enum class ImageType {

             LANDSCAPE_SMALL, LANDSCAPE_LARGE, PORTRAIT_SMALL, PORTRAIT_LARGE
        }

        private const val smallLandscapeSufix = "/landscape_small"
        private const val largeLandscapeSufix = "/landscape_incredible"
        private const val smallPortraiteSufix = "/portrait_small"
        private const val largePortraitSufix = "/portrait_incredible"
    }
}

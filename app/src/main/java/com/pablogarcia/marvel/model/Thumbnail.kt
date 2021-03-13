package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    var path: String?,
    var extension: String?
) : Parcelable {

    fun obtainImage(imageType: ImageType): String {

        val imageSufix = when (imageType) {

            ImageType.LANDSCAPE_SMALL -> smallLandscapeSufix
            ImageType.LANDSCAPE_LARGE -> largeLandscapeSufix
            ImageType.PORTRAIT_SMALL -> smallPortraiteSufix
            ImageType.PORTRAIT_LARGE -> largePortraitSufix
        }

        var imagePrefix = path
        if (path?.indexOf("https") == -1)  {

            imagePrefix = path?.replace("http", "https")
        }
        return "$imagePrefix$imageSufix.$extension"
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

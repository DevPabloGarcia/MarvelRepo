package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    var path: String? = null,
    var extension: String? = null
) : Parcelable {

    fun obtainImage(imageType: ImageType?): String {

        val imageSuffix = when (imageType) {

            ImageType.LANDSCAPE_SMALL -> smallLandscapeSuffix
            ImageType.LANDSCAPE_LARGE -> largeLandscapeSuffix
            ImageType.PORTRAIT_SMALL -> smallPortraitSuffix
            ImageType.PORTRAIT_LARGE -> largePortraitSuffix
            else -> largePortraitSuffix
        }

        var imagePrefix = path
        if (path?.indexOf("https") == -1)  {

            imagePrefix = path?.replace("http", "https")
        }
        return "$imagePrefix$imageSuffix.$extension"
    }

    companion object {

        enum class ImageType {

            LANDSCAPE_SMALL, LANDSCAPE_LARGE, PORTRAIT_SMALL, PORTRAIT_LARGE
        }

        private const val smallLandscapeSuffix = "/landscape_small"
        private const val largeLandscapeSuffix = "/landscape_incredible"
        private const val smallPortraitSuffix = "/portrait_small"
        private const val largePortraitSuffix = "/portrait_incredible"
    }
}

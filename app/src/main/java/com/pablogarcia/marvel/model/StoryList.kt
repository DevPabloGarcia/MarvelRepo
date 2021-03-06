package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<StorySummary>?
) : Parcelable

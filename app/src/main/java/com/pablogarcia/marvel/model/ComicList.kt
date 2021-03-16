package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicList(
    var available: Int? = null,
    var returned: Int? = null,
    var collectionURI: String? = null,
    var items: MutableList<ComicSummary>? = null
): Parcelable

package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<ComicSummary>?
): Parcelable
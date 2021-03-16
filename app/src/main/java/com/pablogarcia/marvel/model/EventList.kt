package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventList(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: List<EventSummary>?
) : Parcelable

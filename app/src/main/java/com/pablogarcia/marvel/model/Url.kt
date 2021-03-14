package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Url(
    var type: String?,
    var url: String?
) : Parcelable

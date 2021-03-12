package com.pablogarcia.marvel.framework.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Character (
    @ColumnInfo(name = "id")
    @NotNull
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @Embedded var thumbnail: Thumbnail? = null
) {

    companion object {
        const val  TABLE_NAME = "character"
    }
}

@Entity
data class Thumbnail(
    @ColumnInfo(name = "path") var path: String? = null,
    @ColumnInfo(name = "extension") var extension: String? = null
)

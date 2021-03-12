package com.pablogarcia.marvel.framework.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Like(
    @ColumnInfo(name = "character_id")
    @NotNull
    @PrimaryKey(autoGenerate = false) var characterId: Int = 0,
    @ColumnInfo(name = "like") var like: Boolean = false
) {
    companion object {

        const val  TABLE_NAME = "like"
    }
}

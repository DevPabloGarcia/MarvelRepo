package com.pablogarcia.marvel.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 3)
abstract class CharactersDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        private const val DATABASE_NAME = "characters_database"

        @Volatile
        private var INSTANCE: CharactersDataBase? = null

        fun getInstance(context: Context) : CharactersDataBase? {

            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDataBase::class.java,
                    DATABASE_NAME
                ).build()
            }

            return INSTANCE
        }
    }
}

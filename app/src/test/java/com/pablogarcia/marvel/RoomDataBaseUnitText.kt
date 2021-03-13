package com.pablogarcia.marvel

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.pablogarcia.marvel.framework.room.CharacterDao
import com.pablogarcia.marvel.framework.room.CharactersDataBase
import com.pablogarcia.marvel.framework.room.Character
import com.pablogarcia.marvel.framework.room.Thumbnail
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException
import org.junit.Assert

@RunWith(RobolectricTestRunner::class)
class RoomDataBaseUnitText {

    private lateinit var characterOne: Character
    private lateinit var characterTwo: Character
    private lateinit var characterThree: Character

    lateinit var characterDao: CharacterDao
    lateinit var database: CharactersDataBase
    lateinit var context: Context


    @Before
    fun configureDataBase() {

        context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, CharactersDataBase::class.java)
            .allowMainThreadQueries()
            .build()
        characterDao = database.characterDao()

        characterOne = Character(
            id = CHARACTER_ID_ONE,
            name = CHARACTER_NAME,
            description = CHARACTER_DESCRIPTION,
            thumbnail = Thumbnail(CHARACTER_THUMBNAIL_PATH, CHARACTER_THUMBNAIL_EXTENSION),
            like = CHARACTER_FAVORITE
        )
        characterTwo = Character(
            id = CHARACTER_ID_TWO,
            name = CHARACTER_NAME,
            description = CHARACTER_DESCRIPTION,
            thumbnail = Thumbnail(CHARACTER_THUMBNAIL_PATH, CHARACTER_THUMBNAIL_EXTENSION),
            like = CHARACTER_NOT_FAVORITE
        )

        characterThree = Character(
            id = CHARACTER_ID_THREE,
            name = CHARACTER_NAME,
            description = CHARACTER_DESCRIPTION,
            thumbnail = Thumbnail(CHARACTER_THUMBNAIL_PATH, CHARACTER_THUMBNAIL_EXTENSION),
            like = CHARACTER_FAVORITE
        )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun writeCharacterAndReadInList() {
        characterDao.insert(characterOne)
        val character = characterDao.getCharacter(CHARACTER_ID_ONE)
        Assert.assertEquals(character.id, CHARACTER_ID_ONE)
        Assert.assertEquals(character.name, CHARACTER_NAME)
        Assert.assertEquals(character.description, CHARACTER_DESCRIPTION)
        Assert.assertEquals(character.thumbnail?.path, CHARACTER_THUMBNAIL_PATH)
        Assert.assertEquals(character.thumbnail?.extension, CHARACTER_THUMBNAIL_EXTENSION)
    }

    @Test
    fun writeCharactersAndReadInList() {

        val charactersList = listOf(characterOne, characterTwo)
        characterDao.insertAll(charactersList)
        val characters = characterDao.getCharacters()
        Assert.assertEquals(characters.size, 2)
        characters[0].run {
            Assert.assertEquals(id, CHARACTER_ID_ONE)
            Assert.assertEquals(name, CHARACTER_NAME)
            Assert.assertEquals(description, CHARACTER_DESCRIPTION)
            Assert.assertEquals(thumbnail?.path, CHARACTER_THUMBNAIL_PATH)
            Assert.assertEquals(thumbnail?.extension, CHARACTER_THUMBNAIL_EXTENSION)
        }
    }

    @Test
    fun writeSameCharacterAndReadInList() {
        characterDao.insert(characterOne)
        characterDao.insert(characterOne)
        characterDao.insert(characterOne)
        val character = characterDao.getCharacter(CHARACTER_ID_ONE)
        Assert.assertEquals(character.name, CHARACTER_NAME)
    }

    @Test
    fun updateCharacterAndReadInList() {
        characterDao.insert(characterOne)
        characterOne.name = CHARACTER_NEW_NAME
        characterDao.update(characterOne)
        val character = characterDao.getCharacter(CHARACTER_ID_ONE)
        Assert.assertEquals(character.name, CHARACTER_NEW_NAME)
    }

    @Test
    fun deleteCharacter() {
        characterDao.insert(characterOne)
        characterDao.delete(characterOne)
        val character = characterDao.getCharacter(CHARACTER_ID_ONE)
        Assert.assertNull(character)
    }

    @Test
    fun getCharactersRow() {
        val charactersList = listOf(characterOne, characterTwo, characterThree)
        characterDao.insertAll(charactersList)
        val rows = characterDao.getCharacterCount()
        Assert.assertEquals(rows, 3)
    }

    @Test
    fun getFavoriteCharacters() {
        val charactersList = listOf(characterOne, characterTwo, characterThree)
        characterDao.insertAll(charactersList)
        val favorites = characterDao.getFavoriteCharacters()
        Assert.assertEquals(favorites.size, 2)
    }

    companion object {

        private const val CHARACTER_ID_ONE = 1
        private const val CHARACTER_ID_TWO = 2
        private const val CHARACTER_ID_THREE = 3
        private const val CHARACTER_NAME = "test_name"
        private const val CHARACTER_NEW_NAME = "test_new_name"
        private const val CHARACTER_DESCRIPTION = "test_description"
        private const val CHARACTER_THUMBNAIL_PATH = "thumbnail_path"
        private const val CHARACTER_THUMBNAIL_EXTENSION = "thumbnail_extension"
        private const val CHARACTER_FAVORITE = 1
        private const val CHARACTER_NOT_FAVORITE = 0
    }
}

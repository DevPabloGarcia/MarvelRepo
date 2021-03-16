package com.pablogarcia.marvel

import com.pablogarcia.marvel.data.mapper.CharactersMapper
import com.pablogarcia.marvel.data.responses.CharactersResponse
import org.junit.Assert
import org.mockito.Mockito.`when` as _when

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharactersMapperUnitTest {

    private lateinit var mapper: CharactersMapper

    @Mock
    lateinit var response: CharactersResponse

    @Mock
    lateinit var dataResponse: CharactersResponse.DataResponse

    @Mock
    lateinit var characterResponse: CharactersResponse.CharacterResponse

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        mapper = CharactersMapper()
        _when(characterResponse.id).thenReturn(ID)
        _when(characterResponse.name).thenReturn(NAME)
        _when(characterResponse.description).thenReturn(DESCRIPTION)
        _when(dataResponse.results).thenReturn(mutableListOf(characterResponse))
        _when(response.data).thenReturn(dataResponse)
    }

    @Test
    fun mapCharacterTest() {
        Assert.assertEquals(mapper.map(response)[0].id, ID)
        Assert.assertEquals(mapper.map(response)[0].name, NAME)
        Assert.assertEquals(mapper.map(response)[0].description, DESCRIPTION)
    }

    companion object {

        private const val ID = 1
        private const val NAME = "test_name"
        private const val DESCRIPTION = "test_description"
    }
}

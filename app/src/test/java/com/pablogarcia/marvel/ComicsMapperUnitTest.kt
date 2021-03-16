package com.pablogarcia.marvel

import com.pablogarcia.marvel.data.mapper.ComicsMapper
import com.pablogarcia.marvel.data.responses.ComicsResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as _when

@RunWith(MockitoJUnitRunner::class)
class ComicsMapperUnitTest {

    private lateinit var mapper: ComicsMapper

    @Mock
    lateinit var response: ComicsResponse

    @Mock
    lateinit var dataResponse: ComicsResponse.DataResponse

    @Mock
    lateinit var comicResponse: ComicsResponse.ComicResponse

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)
        mapper = ComicsMapper()
        _when(comicResponse.id).thenReturn(ID)
        _when(comicResponse.title).thenReturn(TITLE)
        _when(comicResponse.description).thenReturn(DESCRIPTION)
        _when(dataResponse.results).thenReturn(mutableListOf(comicResponse))
        _when(response.data).thenReturn(dataResponse)
    }

    @Test
    fun mapCharacterTest() {
        Assert.assertEquals(mapper.map(response)[0].id, ID)
        Assert.assertEquals(mapper.map(response)[0].title, TITLE)
        Assert.assertEquals(mapper.map(response)[0].description, DESCRIPTION)
    }

    companion object {

        private const val ID = 1
        private const val TITLE = "test_title"
        private const val DESCRIPTION = "test_description"
    }
}

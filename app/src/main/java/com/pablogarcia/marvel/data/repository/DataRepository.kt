package com.pablogarcia.marvel.data.repository

import com.pablogarcia.marvel.model.Character

interface DataRepository {

    /**
     * Obtain all characters
     */
    fun getCharacters(offset: Int, callback: BaseCallback<List<Character>>)
}

package com.pablogarcia.marvel.ui.characters.detail

import androidx.lifecycle.MutableLiveData
import com.pablogarcia.marvel.model.Character
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor() {

    var character : MutableLiveData<Character> = MutableLiveData()

    fun setDataSet(character: Character) {

        this.character.postValue(character)
    }
}

package com.pablogarcia.marvel.ui.characters.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.model.Comic
import com.pablogarcia.marvel.ui.base.BaseViewModel
import com.pablogarcia.marvel.ui.base.UiState
import com.pablogarcia.marvel.usecase.ObtainComicsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var obtainComicsUseCase: ObtainComicsUseCase

    var character : MutableLiveData<Character> = MutableLiveData()
    var comics : MutableLiveData<List<Comic>> = MutableLiveData()

    fun setDataSet(character: Character) {

        this.character.postValue(character)
        uiState.postValue(UiState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = obtainComicsUseCase.get(character.id.toString())) {
                is Result.Success -> {
                    uiState.postValue(UiState.SUCCESS)
                    comics.postValue(result.data)
                }
                else -> uiState.postValue(UiState.ERROR)
            }
        }
    }
}

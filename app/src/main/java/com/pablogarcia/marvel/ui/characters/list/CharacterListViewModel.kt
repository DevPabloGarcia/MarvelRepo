package com.pablogarcia.marvel.ui.characters.list

import androidx.lifecycle.MutableLiveData
import com.pablogarcia.marvel.data.repository.DataException
import com.pablogarcia.marvel.usecase.ObtainCharactersUseCase
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.ui.base.BaseViewModel
import com.pablogarcia.marvel.ui.base.UiState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    var obtainCharactersUseCase: ObtainCharactersUseCase
): BaseViewModel() {

    var characters : MutableLiveData<List<Character>> = MutableLiveData()

    fun onViewCreated() {
        if (characters.value.isNullOrEmpty()) {
            loadCharacters()
        }
    }

    fun loadCharacters() {
        GlobalScope.launch {
            uiState.postValue(UiState.LOADING)
            try {
                val result = obtainCharactersUseCase.get()
                uiState.postValue(UiState.SUCCESS)
                characters.postValue(result.value)
            } catch (ex: DataException) {

                uiState.postValue(UiState.ERROR)
            } finally {

            }
        }
    }
}

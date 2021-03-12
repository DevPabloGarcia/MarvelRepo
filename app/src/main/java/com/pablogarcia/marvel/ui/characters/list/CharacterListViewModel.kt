package com.pablogarcia.marvel.ui.characters.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.usecase.ObtainCharactersUseCase
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.ui.base.BaseViewModel
import com.pablogarcia.marvel.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    var obtainCharactersUseCase: ObtainCharactersUseCase
): BaseViewModel() {

    var characters : MutableLiveData<List<Character>> = MutableLiveData()

    fun onViewCreated() {

        if (characters.value.isNullOrEmpty()) {
            loadCharacters(fromLocal = true)
        }
    }

    fun loadCharacters(fromLocal: Boolean) {

        uiState.postValue(UiState.LOADING)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = obtainCharactersUseCase.get(fromLocal)) {
                is Result.Success -> {
                    uiState.postValue(UiState.SUCCESS)
                    characters.postValue(result.data)
                }
                else -> uiState.postValue(UiState.ERROR)
            }
        }
    }
}

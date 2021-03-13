package com.pablogarcia.marvel.ui.characters.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pablogarcia.marvel.data.repository.Result
import com.pablogarcia.marvel.usecase.ObtainCharactersUseCase
import com.pablogarcia.marvel.model.Character
import com.pablogarcia.marvel.ui.base.BaseViewModel
import com.pablogarcia.marvel.ui.base.UiState
import com.pablogarcia.marvel.usecase.UpdateCharacterLikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(
    private var obtainCharactersUseCase: ObtainCharactersUseCase,
    private var updateCharacterLikeUseCase: UpdateCharacterLikeUseCase
): BaseViewModel() {

    var characters : MutableLiveData<List<Character>> = MutableLiveData()

    /**
     * Called when view is created
     * - Load characters
     */
    fun onViewCreated() {
        if (characters.value.isNullOrEmpty()) {
            loadCharacters(fromLocal = true)
        }
    }

    /**
     * Obtain characters list
     *
     * @param fromLocal - true if need to get characters from local database
     * @param showLoading - true if loading view must be shown
     */
    fun loadCharacters(fromLocal: Boolean, showLoading: Boolean = true) {

        if (showLoading) uiState.postValue(UiState.LOADING)
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

    /**
     * Update like value from character in local database
     *
     * @param character
     */
    fun updateLike(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCharacterLikeUseCase.post(character)
        }
    }
}

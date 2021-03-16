package com.pablogarcia.marvel.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val uiState: MutableLiveData<UiState> = MutableLiveData<UiState>().apply {
        value = UiState.SUCCESS
    }
}

enum class UiState {
    LOADING, SUCCESS, ERROR
}

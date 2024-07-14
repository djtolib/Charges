package com.tolib.charges.data.model

sealed class UiState {
    data object Loading: UiState()
    data object Success: UiState()
    data class Error(val error: String)
}
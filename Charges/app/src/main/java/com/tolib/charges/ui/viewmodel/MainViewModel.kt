package com.tolib.charges.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolib.charges.data.model.ChargeItem
import com.tolib.charges.data.model.UiState
import com.tolib.charges.data.repository.ApiService
import com.tolib.charges.data.repository.ChargeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = ChargeRepository(object : ApiService{})
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState
    lateinit var data: List<ChargeItem>
        private set
    var selectedCity = ""
    fun getCharges(){
        viewModelScope.launch {
            data = repository.getCharges()
            _uiState.emit(UiState.Success)
        }
    }
}
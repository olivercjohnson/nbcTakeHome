package com.robonouveau.nbc.takehome.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robonouveau.nbc.takehome.data.ScreenRepository
import com.robonouveau.nbc.takehome.data.Shelf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShelfScreenViewModel @Inject constructor(
    private val repository: ScreenRepository
) : ViewModel() {

    private val _shelves: MutableStateFlow<List<Shelf>> = MutableStateFlow(emptyList())

    fun shelves(): StateFlow<List<Shelf>> = _shelves

    fun refreshScreen() {
        viewModelScope.launch((Dispatchers.IO)) {
            repository.getHomepage().let { screenResult ->
                if (screenResult.isSuccess) {
                    screenResult.getOrNull()?.let { _shelves.value = it.shelves }
                } else {
                    // TODO: emit an error state back to the UI
                }
            }
        }
    }

}

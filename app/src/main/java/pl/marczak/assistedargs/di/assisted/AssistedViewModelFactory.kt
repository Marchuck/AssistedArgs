package pl.marczak.assistedargs.di.assisted

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface AssistedViewModelFactory<T : ViewModel> {

    fun create(handle: SavedStateHandle): T
}
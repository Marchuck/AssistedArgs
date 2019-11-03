package pl.marczak.assistedargs.di.assisted


import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class AssistedSafeArgsViewModelFactory<T : ViewModel>(
    private val factory: AssistedViewModelFactory<T>,
    owner: SavedStateRegistryOwner,
    private val defaultArgs: Bundle
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        for (key in defaultArgs.keySet()) {
            handle[key] = defaultArgs[key]
        }
        return factory.create(handle) as? T
            ?: throw IllegalStateException("Unknown ViewModel class")
    }
}
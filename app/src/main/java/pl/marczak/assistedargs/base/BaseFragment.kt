package pl.marczak.assistedargs.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import pl.marczak.assistedargs.App
import pl.marczak.assistedargs.MainActivity
import pl.marczak.assistedargs.di.ApplicationComponent
import pl.marczak.assistedargs.di.assisted.AssistedArgsViewModelFactory
import pl.marczak.assistedargs.di.assisted.AssistedViewModelFactory

open class BaseFragment : Fragment() {

    fun argumentsOrEmpty(): Bundle {
        return arguments ?: Bundle.EMPTY
    }

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    inline fun <reified T : ViewModel> assistedViewModel(
        crossinline lazyAssistedViewModelFactory: () -> AssistedViewModelFactory<T>
    ): Lazy<T> =
        viewModels(ownerProducer = { this }, factoryProducer = {
            AssistedArgsViewModelFactory(lazyAssistedViewModelFactory.invoke(), this, argumentsOrEmpty())
        })


    fun findNavController() = (requireActivity() as MainActivity).navController
}
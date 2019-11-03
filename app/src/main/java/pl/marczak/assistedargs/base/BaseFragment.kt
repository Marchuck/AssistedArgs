package pl.marczak.assistedargs.base

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import pl.marczak.assistedargs.App
import pl.marczak.assistedargs.MainActivity
import pl.marczak.assistedargs.di.ApplicationComponent
import pl.marczak.assistedargs.di.assisted.AssistedSafeArgsViewModelFactory
import pl.marczak.assistedargs.di.assisted.AssistedViewModelFactory

open class BaseFragment : Fragment() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (activity?.application as App).appComponent
    }

    inline fun <reified Args : NavArgs> safeNavArgs() = NavArgsLazy(Args::class) {

        Log.d(javaClass.simpleName,"safeNavArgs called")

        for (key in arguments?.keySet() ?: emptySet()) {
            Log.d(javaClass.simpleName,"key = \'$key\', value = \'${arguments?.get(key)}\'")
        }

        arguments ?: run {
            Bundle.EMPTY
        }
    }

    inline fun <reified T : ViewModel> assistedViewModel(
        navArgs: Bundle,
        factory: AssistedViewModelFactory<T>
    ): Lazy<T> =
        viewModels(ownerProducer = { this }, factoryProducer = {
            AssistedSafeArgsViewModelFactory(factory, this, navArgs)
        })


    fun findNavController() = (requireActivity() as MainActivity).navController
}
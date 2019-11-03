package pl.marczak.assistedargs.di

import androidx.fragment.app.ListFragment
import dagger.Component
import pl.marczak.assistedargs.App
import pl.marczak.assistedargs.di.assisted.AssistedFactoriesModule
import pl.marczak.assistedargs.filters.FiltersFragment
import pl.marczak.assistedargs.intro.IntroFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, AssistedFactoriesModule::class]
)
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(fragment: IntroFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: FiltersFragment)
}
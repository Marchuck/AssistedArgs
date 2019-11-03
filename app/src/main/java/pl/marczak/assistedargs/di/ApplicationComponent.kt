package pl.marczak.assistedargs.di

import dagger.Component
import pl.marczak.assistedargs.App
import pl.marczak.assistedargs.di.assisted.ViewModelAssistedFactoriesModule
import pl.marczak.assistedargs.filters.FiltersFragment
import pl.marczak.assistedargs.intro.IntroFragment
import pl.marczak.assistedargs.list.ListFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, ViewModelAssistedFactoriesModule::class]
)
interface ApplicationComponent {
    fun inject(application: App)

    fun inject(fragment: IntroFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: FiltersFragment)
}
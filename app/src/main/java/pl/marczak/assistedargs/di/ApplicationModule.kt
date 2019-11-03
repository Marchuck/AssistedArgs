 package pl.marczak.assistedargs.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import pl.marczak.assistedargs.App
import javax.inject.Singleton

 @Module()
class ApplicationModule(private val application: App) {

    @Provides
    @Singleton
    fun providesResources(): Resources {
        return application.resources
    }

}
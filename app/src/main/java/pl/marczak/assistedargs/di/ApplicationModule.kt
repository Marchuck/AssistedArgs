package pl.marczak.assistedargs.di

import dagger.Module
import pl.marczak.assistedargs.App

@Module
class ApplicationModule(private val application: App)
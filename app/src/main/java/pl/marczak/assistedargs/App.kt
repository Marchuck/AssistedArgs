package pl.marczak.assistedargs

import android.app.Application
import pl.marczak.assistedargs.di.ApplicationComponent
import pl.marczak.assistedargs.di.ApplicationModule
import pl.marczak.assistedargs.di.DaggerApplicationComponent

class App : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
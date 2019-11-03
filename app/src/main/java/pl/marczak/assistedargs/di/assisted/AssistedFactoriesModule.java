package pl.marczak.assistedargs.di.assisted;

import com.squareup.inject.assisted.dagger2.AssistedModule;
import dagger.Module;

@AssistedModule
@Module(includes = AssistedInject_AssistedFactoriesModule.class)
public abstract class AssistedFactoriesModule {
}
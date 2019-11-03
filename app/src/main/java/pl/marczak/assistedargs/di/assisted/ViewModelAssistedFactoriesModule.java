package pl.marczak.assistedargs.di.assisted;


import com.squareup.inject.assisted.dagger2.AssistedModule;

import dagger.Module;

//needed to make assisted magic work!
@AssistedModule
@Module(includes = AssistedInject_ViewModelAssistedFactoriesModule.class)
public abstract class ViewModelAssistedFactoriesModule {
}

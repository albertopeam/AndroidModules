package apa.daggermodule.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import apa.daggermodule.common.ForApplication;
import dagger.Module;
import dagger.Provides;
/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return this.application;
    }


    @Provides
    @Singleton
    Resources provideResources() {
        return this.application.getResources();
    }
}

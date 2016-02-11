package apa.components.global.di.module;

import android.app.Activity;

import dagger.Module;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }
}

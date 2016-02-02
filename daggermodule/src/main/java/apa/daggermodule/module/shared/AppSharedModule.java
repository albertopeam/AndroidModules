package apa.daggermodule.module.shared;

import android.content.Context;
import android.content.SharedPreferences;

import apa.daggermodule.common.ForApplication;
import dagger.Module;
import dagger.Provides;

@Module
public class AppSharedModule {

    private static final String PRIVATE_SHARED_PREFERENCES = "private_shared_preferences";

    public AppSharedModule() {}

    @Provides
    SharedPreferences provideSharedPreferences(@ForApplication Context context) {
        return context.getSharedPreferences(PRIVATE_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

}

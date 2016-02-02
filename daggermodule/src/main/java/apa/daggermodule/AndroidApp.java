package apa.daggermodule;

import android.app.Application;

import apa.daggermodule.component.ApplicationComponent;
import apa.daggermodule.component.DaggerApplicationComponent;
import apa.daggermodule.module.ApplicationModule;

/**
 * Created by alberto on 10/1/16.
 */
public class AndroidApp extends Application implements Graph{


    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        initGraph();
    }


    private void initGraph(){
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }


    @Override
    public ApplicationComponent appComponent() {
        return applicationComponent;
    }
}

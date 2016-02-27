package apa.components.global;

import android.app.Application;

import com.frogermcs.dagger2metrics.Dagger2Metrics;
import com.raizlabs.android.dbflow.config.FlowManager;

import apa.components.BuildConfig;
import apa.components.global.di.Graph;
import apa.components.global.di.component.ApplicationComponent;
import apa.components.global.di.component.DaggerApplicationComponent;
import apa.components.global.di.module.ApplicationModule;

/**
 * Created by alberto on 11/2/16.
 */
public class AndroidApp extends Application implements Graph {


    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Dagger2Metrics.enableCapturing(this);
        }
        initGraph();
        FlowManager.init(this);
    }


    private void initGraph(){
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }


    @Override
    public ApplicationComponent appComponent() {
        return applicationComponent;
    }
}

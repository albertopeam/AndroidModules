package apa.daggermodule.component;

import android.app.Activity;

import apa.daggermodule.common.ForActivity;
import apa.daggermodule.module.shared.AppSharedModule;
import dagger.Component;

/**
 * Created by albertopenasamor on 22/10/15.
 */
@ForActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                AppSharedModule.class,
        }
)
public interface ActivityComponent {
    void inject(Activity activity);
}

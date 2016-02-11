package apa.components.global.di.component;

import android.app.Activity;

import apa.components.global.di.scope.ForActivity;
import apa.components.global.di.module.shared.AppSharedModule;
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

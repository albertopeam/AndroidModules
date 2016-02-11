
package apa.components.global.di.component;

import android.app.Application;

import javax.inject.Singleton;

import apa.components.global.di.module.ApplicationModule;
import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application androidApp);
}

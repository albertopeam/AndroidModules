package apa.components.di;

import apa.accessmodule.LoginActivity;
import apa.daggermodule.common.ForActivity;
import apa.daggermodule.component.ApplicationComponent;
import dagger.Component;

/**
 * Created by alberto on 10/1/16.
 */
@ForActivity
@Component(dependencies = {
                ApplicationComponent.class
            },
            modules = {
                    LoginModule.class
            }
)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

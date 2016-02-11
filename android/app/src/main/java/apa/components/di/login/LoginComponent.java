package apa.components.di.login;

import apa.components.di.ExecutorModule;
import apa.components.global.di.component.ApplicationComponent;
import apa.components.global.di.scope.ForActivity;
import apa.components.ui.LoginActivity;
import dagger.Component;

/**
 * Created by alberto on 10/1/16.
 */
@ForActivity
@Component(dependencies = {
                ApplicationComponent.class
            },
            modules = {
                    LoginModule.class,
                    ExecutorModule.class
            }
)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}

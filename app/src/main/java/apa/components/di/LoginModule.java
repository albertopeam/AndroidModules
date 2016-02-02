package apa.components.di;

import apa.accessmodule.LoginActivity;
import apa.accessmodule.LoginPresenter;
import apa.accessmodule.domain.formvalidator.LoginValidator;
import apa.accessmodule.domain.LoginUseCase;
import apa.daggermodule.common.ForActivity;
import apa.daggermodule.module.ActivityModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 10/1/16.
 */
@Module
public class LoginModule extends ActivityModule{

todo en proyetco principal.... se me fue
    public LoginModule(LoginActivity activity) {
        super(activity);
    }


    @ForActivity
    @Provides
    LoginPresenter providePresenter(LoginValidator loginValidator, LoginUseCase loginUseCase){
        return new LoginPresenter(loginValidator, loginUseCase);
    }


    @ForActivity
    @Provides
    LoginValidator provideValidator(){
        return null;
    }


    @ForActivity
    @Provides
    LoginUseCase provideUseCase(){
        return null;
    }
}

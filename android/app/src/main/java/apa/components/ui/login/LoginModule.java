package apa.components.ui.login;


import apa.accessmodule.domain.formvalidator.validator.LoginValidator;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.accessmodule.ui.presenter.login.LoginPresenter;
import apa.accessmodule.ui.presenter.login.LoginPresenterImpl;
import apa.accessmodule.ui.view.AbsLoginActivity;
import apa.components.global.di.module.ActivityModule;
import apa.components.global.di.scope.ForActivity;
import apa.components.ui.navigation.LoginPage;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 10/1/16.
 */
@Module
public class LoginModule extends ActivityModule{


    public LoginModule(AbsLoginActivity activity) {
        super(activity);
    }


    @ForActivity
    @Provides
    LoginPresenter providePresenter(LoginValidator loginValidator, LoginUseCase loginUseCase, LoginPage loginPage){
        return new LoginPresenterImpl(loginUseCase, (AbsLoginActivity)activity, loginPage);
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


    @ForActivity
    @Provides
    LoginPage provideLoginPage(){
        return null;
    }
}

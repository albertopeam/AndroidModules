package apa.components.ui;

import android.app.FragmentManager;
import android.os.Bundle;

import apa.accessmodule.ui.presenter.login.LoginPresenter;
import apa.accessmodule.ui.presenter.login.LoginPresenterImpl;
import apa.accessmodule.ui.view.AbsLoginActivity;
import apa.components.global.AndroidApp;
import apa.components.global.di.component.ApplicationComponent;
import apa.components.ui.di.DaggerLoginComponent;
import apa.components.ui.di.LoginComponent;
import apa.components.ui.di.LoginModule;

/**
 * Created by alberto on 8/2/16.
 */
public class LoginActivity extends AbsLoginActivity{


    private LoginPresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO:
        //meter db
        //Test integracion con db, tarea rake independiente(como en libro)

        //TODO:  validar
        //presenter con fragment, para reterner estado ante cambios de orientacion por ejemplo, foto pendiente
        //https://www.youtube.com/watch?v=JbUnxCeVxg8 minuto 19

        //TODO: validar -> inyeccion y creacion sin hacer
        initializeInyections();
        initializePresenter();
    }

    private void initializeInyections() {
        ApplicationComponent applicationComponent = ((AndroidApp)getApplication()).appComponent();
        LoginComponent loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(applicationComponent)
                .loginModule(new LoginModule(this))
                .build();
        loginComponent.inject(this);
    }


    private void initializePresenter(){
        FragmentManager fm = getFragmentManager();
        LoginPresenterImpl retainedPresenter = (LoginPresenterImpl) fm.findFragmentByTag(LoginPresenterImpl.class.getSimpleName());
        if (retainedPresenter == null){
            //dagger presenter
            fm.beginTransaction().add(presenter, LoginPresenter.class.getSimpleName()).commit();
        }else{
            //retained presenter
            presenter = retainedPresenter;
        }
        setPresenter(retainedPresenter);
    }
}

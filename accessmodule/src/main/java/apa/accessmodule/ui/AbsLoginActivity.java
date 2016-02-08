package apa.accessmodule.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import apa.accessmodule.R;


/**
 * Created by alberto on 3/1/16.
 */
public abstract class AbsLoginActivity extends AppCompatActivity {


    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.login);
    }

    protected void setView(int reference) {
        setContentView(reference);
    }


    protected void setPresenter(LoginPresenter loginPresenter){
        this.presenter = loginPresenter;
    }


}

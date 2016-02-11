package apa.accessmodule.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import apa.accessmodule.R;
import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.model.LoginForm;
import apa.accessmodule.ui.presenter.login.LoginPresenter;
import apa.accessmodule.ui.presenter.login.LoginView;


/**
 * Created by alberto on 3/1/16.
 */
public abstract class AbsLoginActivity extends AppCompatActivity implements LoginView {


    private LoginPresenter presenter;
    private EditText emailET;
    private EditText passwordET;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.login);
        onCreateActivity();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyActivity();
    }

    private void onDestroyActivity() {
        presenter.destroy();
    }

    private void onCreateActivity() {
        presenter.create();
    }


    protected void setView(int reference) {
        setContentView(reference);
        bindViews();
        setupListeners();
    }


    protected void setPresenter(LoginPresenter loginPresenter){
        this.presenter = loginPresenter;
    }


    protected void bindViews(){
        emailET = (EditText) findViewById(R.id.email);
        passwordET = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    protected void setupListeners() {
        passwordET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    presenter.login(makeForm());
                    return true;
                }
                return false;
            }
        });
    }


    protected LoginForm makeForm(){
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(emailET.getText().toString());
        loginForm.setPassword(passwordET.getText().toString());
        return loginForm;
    }


    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        emailET.setClickable(false);
        passwordET.setClickable(false);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        emailET.setClickable(false);
        passwordET.setClickable(false);
    }

    @Override
    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void logedIn() {
        //TODO:
    }

    @Override
    public void loginError(String error) {
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        Toast.makeText(AbsLoginActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void invalidForm(List<FieldError> fieldErrors) {
        for (FieldError fieldError:fieldErrors){
            if (fieldError.getReference() == emailET.getId()){
                emailET.setError(getResources().getString(fieldError.getError()));
            }else if(fieldError.getReference() == passwordET.getId()){
                passwordET.setError(getResources().getString(fieldError.getError()));
            }
        }
    }

    @Override
    public void clearErrors(){
        emailET.setError(null);
        passwordET.setError(null);
    }
}

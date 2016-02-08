package apa.components.data.api.adapter;

import apa.accessmodule.data.model.cloud.AccountCloud;
import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.*;

/**
 * Created by alberto on 7/2/16.
 */
public class LoginApiAdapter implements apa.accessmodule.data.api.LoginApi {


    private LoginAccountApi loginAccountApi;


    public LoginApiAdapter(LoginAccountApi loginAccountApi) {
        this.loginAccountApi = loginAccountApi;
    }


    @Override
    public AccountCloud login(LoginForm loginForm) throws Exception{
        return null;
    }
}

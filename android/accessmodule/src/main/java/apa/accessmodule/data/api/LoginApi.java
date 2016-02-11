package apa.accessmodule.data.api;

import apa.accessmodule.data.model.cloud.AccountCloud;
import apa.accessmodule.domain.model.LoginForm;

/**
 * Created by alberto on 7/2/16.
 */
public interface LoginApi {
    AccountCloud login(LoginForm loginForm) throws Exception;
}

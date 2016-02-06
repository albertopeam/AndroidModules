package apa.accessmodule.data.api;

import apa.accessmodule.data.entity.AccountEntity;
import apa.accessmodule.domain.model.LoginForm;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginApi {

    @POST("api/accounts")
    AccountEntity login(@Body LoginForm loginForm);
}

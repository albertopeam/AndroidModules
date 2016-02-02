package apa.accessmodule.data.api;

import apa.accessmodule.domain.model.LoginForm;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginApi {

    @POST("api/accounts")
    void login(@Body LoginForm loginForm);
}

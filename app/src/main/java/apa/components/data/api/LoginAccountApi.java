package apa.components.data.api;

import apa.accessmodule.data.model.cloud.AccountCloud;
import apa.accessmodule.domain.model.LoginForm;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginAccountApi {

    @POST("api/accounts")
    AccountCloud login(@Body LoginForm loginForm);
}

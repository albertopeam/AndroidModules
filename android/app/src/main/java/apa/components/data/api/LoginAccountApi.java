package apa.components.data.api;

import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.cloud.AccountCloud;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginAccountApi {

    @POST("/login")
    Call<AccountCloud> login(@Body LoginForm loginForm);
}

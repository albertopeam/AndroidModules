package apa.components.data.api;

import apa.components.data.api.model.AccountCloud;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginApiRest {

    @FormUrlEncoded
    @POST("/login")
    Call<AccountCloud> login(@Field("email") String email, @Field("password") String password);
}

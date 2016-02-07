package apa.accessmodule.data.model.entity;

/**
 * Created by alberto on 7/2/16.
 */
public class AccountEntity {

    private String email;
    private String token;


    public String getEmail() {
        return email != null?email:"";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token!=null?token:"";
    }

    public void setToken(String token) {
        this.token = token;
    }
}

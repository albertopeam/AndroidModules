package apa.accessmodule.domain.repository;

/**
 * Created by alberto on 29/1/16.
 */
public class AccountBoundary {


    private Exception exception;
    private String email;
    private String token;



    public boolean isSuccess(){
        return exception == null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}

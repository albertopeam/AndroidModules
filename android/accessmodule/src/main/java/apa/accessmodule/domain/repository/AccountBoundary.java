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


    public Exception getException() {
        return exception;
    }


    public void setException(Exception exception) {
        this.exception = exception;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof AccountBoundary){
            AccountBoundary accountBoundary = (AccountBoundary) o;
            return (accountBoundary.getEmail().equalsIgnoreCase(this.email) &&
                    accountBoundary.getToken().equalsIgnoreCase(this.token)) ||
                    accountBoundary.getException() != null && this.exception != null &&
                    accountBoundary.getException().getMessage().equalsIgnoreCase(this.getException().getMessage());
        }else{
            return false;
        }
    }
}

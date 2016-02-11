package apa.accessmodule.data.model.cloud;

/**
 * Created by alberto on 30/1/16.
 */
public class AccountCloud {

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

    @Override
    public boolean equals(Object o) {
        if (o instanceof AccountCloud){
            AccountCloud accountCloud = (AccountCloud) o;
            return accountCloud.getToken().equalsIgnoreCase(getToken()) &&
                    accountCloud.getEmail().equalsIgnoreCase(getEmail());
        }else {
            return false;
        }
    }
}

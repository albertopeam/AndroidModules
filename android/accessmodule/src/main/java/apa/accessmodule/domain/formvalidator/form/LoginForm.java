package apa.accessmodule.domain.formvalidator.form;

import java.util.ArrayList;
import java.util.List;

import apa.accessmodule.R;
import apa.accessmodule.domain.formvalidator.model.Field;

/**
 * Created by alberto on 31/1/16.
 */
public class LoginForm implements Form{


    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<Field> fields() {
        List<Field>fields = new ArrayList<>();
        fields.add(new Field(R.id.email, email));
        fields.add(new Field(R.id.password, password));
        return fields;
    }
}

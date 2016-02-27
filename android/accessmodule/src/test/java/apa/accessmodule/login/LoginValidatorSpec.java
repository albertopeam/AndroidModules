package apa.accessmodule.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import apa.accessmodule.R;
import apa.accessmodule.domain.formvalidator.model.Field;
import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.formvalidator.validator.LoginValidator;
import apa.accessmodule.domain.formvalidator.validator.Validator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by alberto on 28/1/16.
 */
public class LoginValidatorSpec {


    private Validator loginValidator;
    private List<Field> fields;


    @Before
    public void setUp(){
        fields = new ArrayList<>();
        loginValidator = new LoginValidator();
    }

    @Test
    public void whenEmailIsntValidThenError(){
        Field emailField = new Field();
        emailField.setReference(R.id.email);
        emailField.setContent("a.com");
        fields.add(emailField);
        List<FieldError>errors = loginValidator.validate(fields);
        assertThat(errors.size(), is(1));
    }


    @Test
    public void whenPasswordIsTooShortThenError(){
        Field passwordField = new Field();
        passwordField.setReference(R.id.password);
        passwordField.setContent("q");
        fields.add(passwordField);
        List<FieldError>errors = loginValidator.validate(fields);
        assertThat(errors.size(), is(1));
    }


    @Test
    public void whenFormIsValidThenNoError(){
        Field emailField = new Field();
        emailField.setReference(R.id.email);
        emailField.setContent("a@gmail.com");
        fields.add(emailField);

        Field passwordField = new Field();
        passwordField.setReference(R.id.password);
        passwordField.setContent("qwerty");
        fields.add(passwordField);

        List<FieldError>errors = loginValidator.validate(fields);

        assertThat(errors.size(), is(0));
    }


    @After
    public void tearDown(){
        loginValidator = null;
    }
}

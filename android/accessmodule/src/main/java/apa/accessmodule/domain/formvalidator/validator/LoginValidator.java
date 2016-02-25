package apa.accessmodule.domain.formvalidator.validator;

import java.util.List;
import java.util.regex.Pattern;

import apa.accessmodule.R;
import apa.accessmodule.domain.formvalidator.model.Field;
import apa.accessmodule.domain.formvalidator.model.FieldError;

/**
 * Created by alberto on 28/1/16.
 */
public class LoginValidator extends Validator {


    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    @Override
    public List<FieldError> validate(List<Field>fieldList) {
        for (Field field:fieldList){
            if (field.getReference() == R.id.email){
                if (!EMAIL_PATTERN.matcher(field.getContent()).matches()){
                    FieldError fieldError = new FieldError();
                    fieldError.setReference(field.getReference());
                    fieldError.setError(R.string.invalid_email);
                    addError(fieldError);
                }
            }else if(field.getReference() == R.id.password){
                if (field.getContent().length() < 6){
                    FieldError fieldError = new FieldError();
                    fieldError.setReference(field.getReference());
                    fieldError.setError(R.string.password_too_length);
                    addError(fieldError);
                }
            }
        }
        return getFieldErrors();
    }
}

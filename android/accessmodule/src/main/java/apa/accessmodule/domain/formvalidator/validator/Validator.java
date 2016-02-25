package apa.accessmodule.domain.formvalidator.validator;

import java.util.ArrayList;
import java.util.List;

import apa.accessmodule.domain.formvalidator.model.Field;
import apa.accessmodule.domain.formvalidator.model.FieldError;

/**
 * Created by alberto on 10/1/16.
 */
public abstract class Validator {


    private List<FieldError>fieldErrors = new ArrayList<>();


    protected List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    protected void addError(FieldError error){
        fieldErrors.add(error);
    }


    public abstract List<FieldError>validate(List<Field> fields);

}

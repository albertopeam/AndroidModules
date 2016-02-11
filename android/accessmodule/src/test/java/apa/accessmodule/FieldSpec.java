package apa.accessmodule;

import org.junit.Test;

import apa.accessmodule.domain.formvalidator.model.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class FieldSpec {

    @Test
    public void whenFieldInitThenAllValuesAreSet() throws Exception {
        Field field = new Field();
        field.setReference(1);
        field.setContent("albertopeam@gmail.com");
        assertThat(field.getReference(), equalTo(1));
        assertThat(field.getContent(), equalTo("albertopeam@gmail.com"));
    }
}
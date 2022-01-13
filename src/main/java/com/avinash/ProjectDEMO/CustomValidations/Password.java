package com.avinash.ProjectDEMO.CustomValidations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Constraint(validatedBy = PasswordValid.class)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "1. Password should contain minimum 10 letters \n" +
            " 2.Password should contain Upper case letter " +
            "\n 3.password should contain lower case letter " +
            "\n 4.password should contain a Special Character"+
            "\n 5.password should not have a white space";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

package com.example.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDtoValidator.class)
public @interface ValidDto {

    interface ShouldFail { }

    String message() default "some-error-message";

    Class<ShouldFail>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

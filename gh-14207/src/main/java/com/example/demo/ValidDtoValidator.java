package com.example.demo;

import com.example.demo.ValidDto.ShouldFail;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDtoValidator implements ConstraintValidator<ValidDto, SomeDto> {
    private boolean shouldFail = false;

    // @VisibleForTesting
    static Class<ShouldFail>[] lastGroup;

    @Override
    public void initialize(ValidDto constraintAnnotation) {
        Class<ShouldFail>[] groups = constraintAnnotation.groups();
        lastGroup = groups;

        if (groups.length > 0) {
            shouldFail = true;
        }
    }

    @Override
    public boolean isValid(SomeDto someDto, ConstraintValidatorContext constraintValidatorContext) {
        // if ShouldFail group exists, then make validation fail.
        return !shouldFail;
    }
}

package com.fishingclub.main.validations;

import com.fishingclub.main.validations.implementations.LocalDateValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateValidatorImpl.class)
public @interface LocalDateValidator {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

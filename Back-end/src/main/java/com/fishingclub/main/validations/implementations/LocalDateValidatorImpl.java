package com.fishingclub.main.validations.implementations;

import com.fishingclub.main.validations.LocalDateValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateValidatorImpl implements ConstraintValidator<LocalDateValidator, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^20\\d{2}-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])$");
        Matcher matcher = pattern.matcher(localDate.toString());

        return matcher.matches();
    }
}

package com.fishingclub.main.validations.implementations;

import com.fishingclub.main.enums.IdentityDocumentType;
import com.fishingclub.main.validations.IdentityTypeValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class IdentityTypeValidation implements ConstraintValidator<IdentityTypeValidator, IdentityDocumentType> {
    @Override
    public boolean isValid(IdentityDocumentType idType, ConstraintValidatorContext constraintValidatorContext) {
        List<IdentityDocumentType> idTypesList = Arrays.asList(IdentityDocumentType.CIN, IdentityDocumentType.RESIDENCE_CARD, IdentityDocumentType.PASSPORT);
        return idTypesList.contains(idType);
    }
}

package com.freedom.misc.util.validatorutil;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValidator, String> {

    private EnumValidator enumValidator;

    @Override
    public void initialize(EnumValidator enumValidator) {
        this.enumValidator = enumValidator;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {

        if (enumValidator.isBlankAllowed() && StringUtils.isBlank(valueForValidation)) {
            return true;
        }

        for (Enum enumValue : this.enumValidator.enumClass().getEnumConstants()) {

            if (StringUtils.isNotBlank(valueForValidation)
                    && valueForValidation.equalsIgnoreCase(enumValue.toString())) {
                return true;
            }
        }

        return false;
    }
}
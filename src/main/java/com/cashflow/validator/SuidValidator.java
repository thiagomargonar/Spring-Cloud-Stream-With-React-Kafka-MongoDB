package com.cashflow.validator;

import com.cashflow.annotations.Document;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class SuidValidator implements ConstraintValidator<Document, CharSequence> {

    private final Pattern pattern = Pattern.compile("^\\d{11}$|^\\d{14}$");

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasLength(value)) {
            return false;
        }

        return pattern.matcher(value).matches();
    }
}

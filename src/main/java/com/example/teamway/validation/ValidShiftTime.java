package com.example.teamway.validation;


import com.example.teamway.validation.impl.ValidShiftTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidShiftTimeValidator.class)
@Documented
public @interface ValidShiftTime {
    String message() default "Invalid shift start time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


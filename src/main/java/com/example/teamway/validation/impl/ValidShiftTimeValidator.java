package com.example.teamway.validation.impl;


import com.example.teamway.validation.ValidShiftTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

//@Component
public class ValidShiftTimeValidator implements ConstraintValidator<ValidShiftTime, LocalDateTime> {

    private static final List<String> VALID_TIMES = Arrays.asList("08:00", "12:00", "18:00");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void initialize(ValidShiftTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        LocalTime localTime = localDateTime.toLocalTime();
        String formattedTime = localTime.format(TIME_FORMATTER);
        return VALID_TIMES.contains(formattedTime);
    }
}


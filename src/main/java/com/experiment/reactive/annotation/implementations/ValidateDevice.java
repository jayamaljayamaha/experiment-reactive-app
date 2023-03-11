package com.experiment.reactive.annotation.implementations;

import com.experiment.reactive.annotation.annotations.DeviceValidator;
import com.experiment.reactive.common.Device;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValidateDevice implements ConstraintValidator<DeviceValidator, String> {

    @Override
    public boolean isValid(String capturedBy, ConstraintValidatorContext context) {
        return Arrays.stream(Device.values()).map(Enum::name).toList().contains(capturedBy);
    }
}

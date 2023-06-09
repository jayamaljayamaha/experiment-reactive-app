package com.experiment.reactive.annotation.annotations;


import com.experiment.reactive.annotation.implementations.ValidateDevice;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidateDevice.class)
public @interface DeviceValidator {

    String message() default "Invalid device type";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

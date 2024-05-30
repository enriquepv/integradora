package org.ilozano.proyecto_dam_daw_2.validations.registerValidations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MismaClaveValidator.class)
public @interface MismaClave {
    String message() default "{MismaClave.usuario.confirmarClave}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

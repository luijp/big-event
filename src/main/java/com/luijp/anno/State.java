package com.luijp.anno;

import com.luijp.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { StateValidation.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface State {

    String message() default "State 参数的值只能是 已发布 或者 草稿";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

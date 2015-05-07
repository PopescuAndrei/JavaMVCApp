package ro.teamnet.zth.api.annotations;

import javax.lang.model.type.PrimitiveType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Andrei on 5/6/2015.
 */
@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface ParameterAnnotation {
    Class parameterType() default Integer.class;
    String parameterName();
}

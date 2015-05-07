package ro.teamnet.zth.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by Andrei on 5/6/2015.
 */

@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface MyController {
    String urlPath();
}

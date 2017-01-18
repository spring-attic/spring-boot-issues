package sample.aop.monitor;

import java.lang.annotation.*;

/**
 * Created by serv on 2014/11/27.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Aop {
}

package org.springframework.boot.issues.gh1539.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * JSR303 validator component.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 * @param <T>
 */
@Component
public class ValidatorComponent<T> {

    @Autowired
    /**
     *
     *
     * If you enable Qualifier, then application can run in the container and
     * standalone (embedded) mode. But if you remove it, then the application
     * doesn't run in the container but run in standalone mode.
     *
     * It throws an exception:
     * Caused by:
     * org.springframework.beans.factory.NoUniqueBeanDefinitionException:
     * No qualifying bean of type [javax.validation.Validator] is defined:
     * expected single matching bean but found 2:
     * localValidatorFactoryBean,mvcValidator
     *
     *
     */
    //@Qualifier(value = "localValidatorFactoryBean")
    private Validator validator;

    public void validate(T bean) {
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation : violations) {
                throw new RuntimeException("Sample");
            }
        }
    }
}

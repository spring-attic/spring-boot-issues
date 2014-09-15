package org.springframework.boot.issues.gh1530.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.BusinessLogicException;
import org.springframework.boot.issues.gh1530.annotation.TransactionLogger;
import org.springframework.boot.issues.gh1530.model.Transaction;
import org.springframework.boot.issues.gh1530.service.TransactionLoggerComponent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Component
public class TransactionLoggerAspect {

    Logger logger = LoggerFactory.getLogger(TransactionLoggerAspect.class);

    @Autowired
    protected TransactionLoggerComponent transactionLoggerComponent;

    @Around("execution(@org.springframework.boot.issues.gh1530.annotation.TransactionLogger * *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        TransactionLogger annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TransactionLogger.class);

        Transaction transaction = (Transaction) joinPoint.getArgs()[0];

        logger.info("TRANSACTION_LOGGER_START, {}", transaction);
        transactionLoggerComponent.logTransaction(transaction);

        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();

            transaction.setStatus(Transaction.STATUS_OK);
            logger.info("TRANSACTION_LOGGER_END: OK. {}", transaction);

            /**
             * 
             * 
             * When I throw a RuntimeException, then my ExampleServiceIT test
             * has been failed:
             * "expected:<null> but was:<Hello World! Your input is: my_input>"
             *
             * Expected behaviour: rollback business transaction
             * (@Transacional) when a RuntimeException thrown in
             * TransactionLoggerAspect.
             *
             * IMHO, it is not correct behaviour, because my Application config
             * has @EnableTransactionManagement(order =
             * Ordered.LOWEST_PRECEDENCE - 20), and TransactionLoggerAspect
             * class has a @Order(Ordered.LOWEST_PRECEDENCE - 1) annotation.
             *
             * 
             */
            if (true) {
                // please test with remove this comment -->>
                // throw new RuntimeException("Text exception: DB connection has been lost!");
            }

            transactionLoggerComponent.updateTransaction(transaction);

        } catch (BusinessLogicException ex) {
            transaction.setStatus(Transaction.STATUS_ERROR);
            logger.info("TRANSACTION_LOGGER_END: ERROR. {}", transaction);
            transactionLoggerComponent.updateTransaction(transaction);
            throw ex;

        } catch (Exception ex) {
            transaction.setStatus(Transaction.STATUS_EXCEPTION);
            logger.info("TRANSACTION_LOGGER_END: EXCEPTION. {}", transaction);
            transactionLoggerComponent.updateTransaction(transaction);
            throw ex;

        }
        return returnValue;
    }

}

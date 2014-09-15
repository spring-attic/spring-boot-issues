package org.springframework.boot.issues.gh1530.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.issues.gh1530.BusinessLogicException;
import org.springframework.boot.issues.gh1530.annotation.MessageLogger;
import org.springframework.boot.issues.gh1530.model.Message;
import org.springframework.boot.issues.gh1530.model.Transaction;
import org.springframework.boot.issues.gh1530.service.MessageLoggerComponent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Message logger.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class MessageLoggerAspect {

    Logger logger = LoggerFactory.getLogger(MessageLoggerAspect.class);

    @Autowired
    protected MessageLoggerComponent messageLoggerComponent;

    @Around("execution(@org.springframework.boot.issues.gh1530.annotation.MessageLogger * *(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MessageLogger annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(MessageLogger.class);

        Transaction transaction = (Transaction) joinPoint.getArgs()[0];
        Message message = (Message) joinPoint.getArgs()[1];

        logger.info("MESSAGE_LOGGER_INPUT, {}, {}", transaction, message);
        messageLoggerComponent.logMessage(message);

        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();

            message.setMessage((String) returnValue);
            message.setStatus(Message.STATUS_OK);
            logger.info("MESSAGE_LOGGER_OUTPUT: OK. {}, {}", transaction, returnValue);
            messageLoggerComponent.logMessage(message);

        } catch (BusinessLogicException ex) {
            String errorMessageCode = ex.getMessage();
            message.setStatus(Message.STATUS_ERROR);
            message.setMessage(errorMessageCode);
            logger.info("MESSAGE_LOGGER_OUTPUT: ERROR. {}, {}", transaction, errorMessageCode);
            messageLoggerComponent.logMessage(message);
            throw ex;

        } catch (Exception ex) {
            message.setStatus(Message.STATUS_EXCEPTION);
            String exMessage = ex.getMessage();
            message.setMessage(exMessage);
            logger.info("MESSAGE_LOGGER_OUTPUT: EXCEPTION. {}, {}", transaction, exMessage);
            messageLoggerComponent.logMessage(message);
            throw ex;

        }
        return returnValue;
    }

}

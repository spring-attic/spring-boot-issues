package com.gh1216.exception.handler;

import com.gh1216.exception.BaseException;
import com.gh1216.exception.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class TransactionExceptionHandler {
    @ExceptionHandler(value = {TransactionSystemException.class})
    public ResponseEntity<BaseException> handleTxException(TransactionSystemException ex) {
        BaseException be = new BaseException();
        be.setStatusCode(StatusCodes.TRANSACTION_SYSTEM_ERROR);
        Throwable t = ex.getCause();
        if(t instanceof ConstraintViolationException){
            ConstraintViolationException cve = (ConstraintViolationException)t;
            Set violations = cve.getConstraintViolations();
            ConstraintViolation v = (ConstraintViolation) violations.toArray()[0];
            be.setMessage(v.getPropertyPath() + " " + v.getMessage());
        }else {
            be.setMessage(ex.getMessage());
        }
        return new ResponseEntity(be, HttpStatus.BAD_REQUEST);
    }
}

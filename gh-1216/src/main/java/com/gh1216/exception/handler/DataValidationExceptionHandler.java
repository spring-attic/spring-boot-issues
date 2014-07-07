package com.gh1216.exception.handler;

import com.gh1216.exception.BaseException;
import com.gh1216.exception.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class DataValidationExceptionHandler {
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<BaseException> handleBadInput(ConstraintViolationException ex) {
        BaseException be = new BaseException();
        be.setStatusCode(StatusCodes.DATA_VALIDATION_ERROR);
        Set vioations = ex.getConstraintViolations();
        ConstraintViolation v = (ConstraintViolation) vioations.toArray()[0];
        be.setMessage(v.getPropertyPath() + " " + v.getMessage());
        return new ResponseEntity(be, HttpStatus.BAD_REQUEST);
    }
}

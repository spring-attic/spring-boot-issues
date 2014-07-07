package com.gh1216.exception.handler;

import com.gh1216.exception.BaseException;
import com.gh1216.exception.StatusCodes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DataIntegrityExceptionHandler {

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<BaseException> handleException(DataIntegrityViolationException ex) {
        BaseException be = new BaseException();
        be.setStatusCode(StatusCodes.DATA_INTEGRITY_ERROR);
        be.setMessage("Data constraint violated. " + ex.getMessage());
        return new ResponseEntity(be, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

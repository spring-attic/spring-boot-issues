package com.gh1216.exception.handler;

import com.gh1216.exception.BaseException;
import com.gh1216.exception.StatusCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<BaseException> handleBadInput(Exception ex) {
        BaseException be = new BaseException();
        be.setStatusCode(StatusCodes.UNKNOWN_SYSTEM_ERROR);
        be.setMessage(ex.getMessage());
        return new ResponseEntity(be, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

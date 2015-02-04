package com.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @RequestMapping(value = "/domainObject", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> setDomainObject(@RequestBody DomainObject obj) {
        System.out.println(obj);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }
}

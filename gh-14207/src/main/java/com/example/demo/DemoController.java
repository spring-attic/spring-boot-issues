package com.example.demo;

import com.example.demo.ValidDto.ShouldFail;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/works", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK)
    public String works(@Validated @RequestBody SomeDto someDto) {
        return "works";
    }

    @RequestMapping(value = "/should-fail", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK)
    public String shouldFail(@Validated(ShouldFail.class) @RequestBody SomeDto someDto) {
        return "should have already failed";
    }
}

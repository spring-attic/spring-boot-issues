package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class DemoController {
    @Autowired
    private MappingJackson2XmlHttpMessageConverter xmlConverter;

    @Value("classpath:FooResponse.xml")
    private Resource rawResponseData;

    private FooResponse getFoo() throws IOException {
        ObjectMapper mapper = xmlConverter.getObjectMapper();

        return mapper.readValue(rawResponseData.getInputStream(), FooResponse.class);
    }

    @RequestMapping("/test")
    public BarResponse test1() throws IOException {
        FooResponse serviceResponse = getFoo();
        BarResponse response = new BarResponse();

        response.setCreatedAt(serviceResponse.getSuccessfull().get(0).getDate());
        //        response.setCreatedAt(ZonedDateTime.now());

        return response;
    }

    @RequestMapping
    public TestResponse test2() {
        return new TestResponse();
    }
}

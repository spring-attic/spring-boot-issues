package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;

    private FooResponse getFoo() throws IOException, URISyntaxException {
        URI uri = new URI("http://www.mocky.io/v2/56faab45100000e402687857");

        return restTemplate.getForObject(uri, FooResponse.class);
    }

    @RequestMapping("/test")
    public BarResponse test1() throws IOException, URISyntaxException {
        FooResponse serviceResponse = getFoo();
        BarResponse response = new BarResponse();

        response.setCreatedAt(serviceResponse.getSuccessfull().get(0).getDate());

        return response;
    }

    @RequestMapping
    public TestResponse test2() {
        return new TestResponse();
    }
}

package com.example.executorissue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class EchoController {

    @GetMapping("/echo")
    Flux<String> echo() {
        return Flux.just("echo");
    }
}

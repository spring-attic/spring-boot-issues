package com.topriddy.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/greeting")
    public String index() {
        return "index";
    }
}
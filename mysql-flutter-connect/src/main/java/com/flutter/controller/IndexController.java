package com.flutter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/") //http://localhost:8080/
public class IndexController {

	@GetMapping
    public String sayHello() {
        return "Hello and Welcome to the application.";
    }
}

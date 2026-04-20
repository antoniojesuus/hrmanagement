package com.ntt.hrmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HrController {

    @GetMapping("/hello")
    public String hello() {
        return "Hola, funciona!";
    }
}

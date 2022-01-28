package com.IV1201VT221.IV1201.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restcontroller {
    @GetMapping("/test")
    public String indexTest(){
        return "Connection working";
    }

}

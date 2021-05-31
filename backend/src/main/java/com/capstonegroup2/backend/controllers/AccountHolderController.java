package com.capstonegroup2.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountHolderController {

    @GetMapping("/Test")
    public String test() {
        return "<h1>Hello World!</h1>";
    }

}

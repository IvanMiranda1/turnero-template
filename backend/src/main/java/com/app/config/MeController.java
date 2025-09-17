package com.app.config;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class MeController {
    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }
 
    @GetMapping("/public/ping")
    public String ping() {
        return "pong";
    }
}

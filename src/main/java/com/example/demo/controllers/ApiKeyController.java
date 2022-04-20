package com.example.demo.controllers;

import com.example.demo.model.ApiKey;
import com.example.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("key")
public class ApiKeyController {
    private final AuthService authService;

    @GetMapping("all-active")
    public List<ApiKey> getAllActiveKeys() {
        return authService.getAllApiKeys();
    }

    @PostMapping("validate")
    public Boolean validateKey(@RequestParam String key) {
        return authService.validateKey(key);
    }

    @PostMapping("generate")
    public ApiKey generateKey() {
        return authService.addKey();
    }

    @PostMapping("delete")
    public String deleteKey(@RequestParam String key) {
        authService.deleteKey(key);
        return "Succesfuly deleted key.";
    }

}

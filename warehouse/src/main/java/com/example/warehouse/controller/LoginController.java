package com.example.warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Kulon controller a bejelentkezesi oldal kiszolgalasara.
@Controller
public class LoginController {

    // A Spring Security erre az utvonalra iranyitja a nem bejelentkezett felhasznalokat.
    @GetMapping("/login")
    public String login() {
        // A "login" nev a templates/login.html Thymeleaf sablonra mutat.
        return "login";
    }
}

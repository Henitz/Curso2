package com.sistema.api.home;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class homeController {
    @GetMapping
    public String home() {
        String s = "Seja Bem Vindo!!!";
        return s;
    }
}

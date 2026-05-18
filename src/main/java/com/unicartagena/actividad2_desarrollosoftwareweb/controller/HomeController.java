package com.unicartagena.actividad2_desarrollosoftwareweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controlador para la pagina de inicio
@Controller
public class HomeController {

    // GET / — Muestra la pagina de inicio
    @GetMapping("/")
    public String home() {
        // Apunta a templates/home.html
        return "home";
    }
}
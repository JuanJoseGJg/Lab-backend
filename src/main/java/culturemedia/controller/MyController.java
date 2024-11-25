package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Ruta base para todas las peticiones del controlador
public class MyController {

    // Método GET para devolver un saludo
    @GetMapping("/greet")
    public String greet() {
        return "Hello, welcome to the Spring Boot REST API!";
    }

    // Método POST para recibir datos en formato JSON y devolver un mensaje
    @PostMapping("/data")
    public String postData(@RequestBody DataRequest data) {
        return "Received data: " + data.getName();
    }

    // Clase interna para representar la solicitud POST
    public static class DataRequest {
        private String name;

        // Getter y Setter para el atributo 'name'
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

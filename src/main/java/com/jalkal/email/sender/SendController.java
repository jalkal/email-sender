package com.jalkal.email.sender;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SendController {

    @PostMapping("/send")
    public void send(@RequestParam String name, @RequestParam String email,
                     @RequestParam(required = false) String phone, @RequestParam String message){

        System.out.println("======= Mensaje recibido =======================");
        System.out.println("Nombre: " + name);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + phone);
        System.out.println("Mensaje: " + message);
        System.out.println("------------------------------------------------");
    }
}

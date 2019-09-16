package com.jalkal.email.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin
@RestController
public class SendController {

    @Autowired
    private EmailSender emailSender;

    @PostMapping("/send")
    public void send(@RequestParam String name, @RequestParam String email,
                     @RequestParam(required = false) String phone, @RequestParam String message) {
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("\n===== Mensaje recibido =======\n")
            .append("Fecha: ").append(LocalDateTime.now())
            .append("Nombre: ").append(name).append("\n")
            .append("Email: ").append(email).append("\n")
            .append("Telefono: ").append(phone).append("\n")
            .append("Mensaje: ").append(message).append("\n")
            .append("-----------------------\n");

        System.out.print(emailContent.toString());
        emailSender.send(email, emailContent.toString().replace("\n", "<br>"));
    }
}

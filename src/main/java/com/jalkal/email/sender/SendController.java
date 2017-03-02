package com.jalkal.email.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SendController {

    @Autowired
    private EmailSender emailSender;

    @PostMapping("/send")
    public void send(@RequestParam String name, @RequestParam String email,
                     @RequestParam(required = false) String phone, @RequestParam String message) throws Exception {

        StringBuilder emailContent = new StringBuilder();

        emailContent.append("\n===== Mensaje recibido =======\n")
        .append("Nombre: ").append(name).append("\n")
        .append("Email: ").append(email).append("\n")
        .append("Telefono: ").append(phone).append("\n")
        .append("Mensaje: ").append(message).append("\n")
        .append("-----------------------\n");

        System.out.print(emailContent.toString());
        if(!emailSender.send(emailContent.toString())){
            throw new Exception("No se ha podido enviar el email");
        }
    }
}

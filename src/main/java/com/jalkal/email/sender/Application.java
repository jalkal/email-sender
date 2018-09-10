package com.jalkal.email.sender;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormParserFactory;
import io.undertow.util.Headers;

public class Application {

    public static void main(String[] args) {

        EmailSender emailSender = new EmailSenderImpl("", "", "", "", "");
        Undertow server = Undertow.builder()
                .addHttpListener(8082, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        FormData formData = FormParserFactory.builder().build().createParser(exchange).parseBlocking();
                        String name = formData.getFirst("name").getValue();
                        String email = formData.getFirst("email").getValue();
                        String phone = formData.getFirst("phone").getValue();
                        String message = formData.getFirst("message").getValue();

                        StringBuilder emailContent = new StringBuilder();

                        emailContent.append("\n===== Mensaje recibido =======\n")
                                .append("Nombre: ").append(name).append("\n")
                                .append("Email: ").append(email).append("\n")
                                .append("Telefono: ").append(phone).append("\n")
                                .append("Mensaje: ").append(message).append("\n")
                                .append("-----------------------\n");

                        System.out.print(emailContent.toString());
                        emailSender.send(emailContent.toString());
                    }
                }).build();
        server.start();
    }
}
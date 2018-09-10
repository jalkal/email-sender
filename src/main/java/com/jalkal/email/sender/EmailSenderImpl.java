package com.jalkal.email.sender;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSenderImpl implements EmailSender {

    Properties properties = new Properties();
    private final String username;
    private final String password;
    private final String from;
    private final String to;
    private final String subject;

    public EmailSenderImpl(String username, String password, String from, String to, String subject) {
        this.subject = subject;
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        this.username = username;
        this.password = password;
        this.from = from;
        this.to = to;
    }

    public void send(String text) {
        try {
            Session session = Session.getInstance(properties,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

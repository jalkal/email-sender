package com.jalkal.email.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by user on 02/03/2017.
 */
@Configuration
public class AppConfig {

    @Value("${email.user}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.subject}")
    private String subject;

    @Bean
    public EmailSender emailSender(){

        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return (replyTo, content) -> {
            try {
                Session session = Session.getDefaultInstance(properties, null);

                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setReplyTo((new InternetAddress[]{new InternetAddress(replyTo)}));
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("eva.h.pelu@gmail.com"));
                mimeMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress("jalcalde@gmail.com"));
                mimeMessage.setSubject(subject);
                mimeMessage.setContent(content, "text/html");

                Transport transport = session.getTransport("smtp");

                transport.connect("smtp.gmail.com", username, password);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                transport.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        };
    }
}

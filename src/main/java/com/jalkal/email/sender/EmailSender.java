package com.jalkal.email.sender;

/**
 * Created by user on 02/03/2017.
 */
public interface EmailSender {
    void send(String replyTo, String message);
}

package com.storichallenge.service;

public interface EmailService {
    void sendEmail(String to, String fileName);
    void sendEMail(String[] to, String fileName);
    String createBodyEmail(String fileName);
}

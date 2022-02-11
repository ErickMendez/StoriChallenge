package com.storichallenge.controller;

import com.storichallenge.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    EmailService emailService;

    @GetMapping("/statement-of-account/{to}/{fileName}")
    public ResponseEntity<String> statementOfAccount(@PathVariable String to,@PathVariable String fileName){
        try {
            emailService.sendEmail(to, fileName);
            return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR + ": " + e.getMessage());
        }
    }
}

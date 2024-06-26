package com.example.VirtualFitON.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendOrderConfirmationEmail(String to, String orderId, String orderDetails) {
        String subject = "Order Confirmation - Order #" + orderId;
        String text = "Dear customer,\n\nYour order has been successfully placed.\n\nOrder details:\n" + orderDetails + "\n\nThank you for shopping with us!";
        sendSimpleMessage(to, subject, text);
    }

}

package com.example.demo;

public class OrderService {
    EmailService notification = new EmailService();
    public void placeorder() {
        System.out.println("Order Placed");
        notification.sendNotification();
    }
}

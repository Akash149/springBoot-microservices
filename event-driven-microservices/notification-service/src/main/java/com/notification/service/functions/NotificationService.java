package com.notification.service.functions;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.notification.service.dtos.OrderInfo;

@Configuration
public class NotificationService {

    @Bean
    public Supplier<String> testing() {
        return ()-> "This is testing";
    }

    @Bean
    public Function<String, String> sayHello() {
        return (message) -> {
            return "Hello, How are you? " + message;
        };
    }

    @Bean
    public Function<OrderInfo, String> orderNotification() {
        return (orderInfo) -> {
            // Logic to send notification
            sendNotification(orderInfo);
            System.out.println("Order Id: " + orderInfo.getOrderId());
            System.out.println("Order User Id: " + orderInfo.getUserId());
            System.out.println("Order Email: " + orderInfo.getEmailId());
            System.out.println("Order Phone: " + orderInfo.getPhoneNo());
            return "Notification sent to " + orderInfo.getEmailId();
            };
        }
            
    private void sendNotification(OrderInfo orderInfo) {
        // Logic to send notification
        System.out.println("Notification sent to " + orderInfo.getEmailId());
    }    

}

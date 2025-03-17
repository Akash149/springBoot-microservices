package com.notification.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    private String orderId;
    private String userId;
    private String createdDate;
    private String price;
    private String emailId;
    private String phoneNo;

}

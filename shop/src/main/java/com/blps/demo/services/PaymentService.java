package com.blps.demo.services;

import com.blps.demo.entity.Payment;
import com.blps.demo.entity.ProductOrder;

public interface PaymentService {
    Payment addPayment(int receive, String processor, int productOrderId);

    Payment getPayment(int productOrderId);
}

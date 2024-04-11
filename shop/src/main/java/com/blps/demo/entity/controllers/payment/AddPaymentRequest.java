package com.blps.demo.entity.controllers.payment;

public record AddPaymentRequest(int receive, String processor, int productOrderId) {
}

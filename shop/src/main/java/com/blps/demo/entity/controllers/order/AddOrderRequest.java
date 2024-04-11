package com.blps.demo.entity.controllers.order;

public record AddOrderRequest(Integer clientId, String pickupPointAddress) {
}

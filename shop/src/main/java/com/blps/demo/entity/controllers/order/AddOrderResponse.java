package com.blps.demo.entity.controllers.order;

import java.util.List;

public record AddOrderResponse(Integer id, List<AddOrderResponseItem> items, Integer deliveryTime, Integer costOfDelivery, String paymentMethod) {

}

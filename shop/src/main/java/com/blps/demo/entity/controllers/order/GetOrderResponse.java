package com.blps.demo.entity.controllers.order;

import com.blps.demo.entity.controllers.item.ItemWithStatus;

import java.util.List;

public record GetOrderResponse(Integer orderId, Integer clientId, List<ItemWithStatus> items) {}

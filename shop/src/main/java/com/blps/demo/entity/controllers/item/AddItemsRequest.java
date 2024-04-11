package com.blps.demo.entity.controllers.item;

import java.util.List;

public record AddItemsRequest(Integer sellerId, String sellerName, List<Item> items) {}


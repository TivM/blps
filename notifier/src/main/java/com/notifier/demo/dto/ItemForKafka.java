package com.notifier.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemForKafka(
    @JsonProperty("id") Integer id,
    @JsonProperty("status") String status,
    @JsonProperty("clientEmail") String clientEmail,
    @JsonProperty("sellerEmail") String sellerEmail,
    @JsonProperty("orderId") Integer orderId,
    @JsonProperty("address") String address
){}

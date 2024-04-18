package com.blps.demo.entity.controllers.item;

import com.blps.demo.entity.OrderedItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemForKafka {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("clientEmail")
    private String clientEmail;
    @JsonProperty("sellerEmail")
    private String sellerEmail;
    @JsonProperty("orderId")
    private Integer orderId;
    @JsonProperty("address")
    private String address;

    public ItemForKafka(OrderedItem orderedItem) {
        this.id = orderedItem.getId();
        this.status = orderedItem.getStatus();
        this.clientEmail = orderedItem.getProductOrder().getClient().getEmail();
        this.sellerEmail = orderedItem.getProduct().getSeller().getEmail();
        this.orderId = orderedItem.getProductOrder().getId();
        this.address = orderedItem.getProductOrder().getPickupPointAddress();
    }
}

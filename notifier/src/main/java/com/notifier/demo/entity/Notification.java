package com.notifier.demo.entity;

import com.notifier.demo.dto.ItemForKafka;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "notification")
@Accessors(chain = true)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "client_email", nullable = false)
    private String clientEmail;

    @Column(name = "seller_email", nullable = false)
    private String sellerEmail;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_sent", nullable = false)
    private Integer isSent;

    public static Notification of(ItemForKafka itemForKafka) {
        return new Notification()
                .setProductId(itemForKafka.id())
                .setStatus(itemForKafka.status())
                .setClientEmail(itemForKafka.clientEmail())
                .setSellerEmail(itemForKafka.sellerEmail())
                .setOrderId(itemForKafka.orderId())
                .setAddress(itemForKafka.address())
                .setIsSent(0);
    }
}

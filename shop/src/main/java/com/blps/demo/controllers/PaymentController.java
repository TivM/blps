package com.blps.demo.controllers;

import com.blps.demo.entity.Payment;
import com.blps.demo.entity.controllers.payment.AddPaymentRequest;
import com.blps.demo.entity.controllers.payment.AddPaymentResponse;
import com.blps.demo.entity.controllers.payment.GetPaymentResponse;
import com.blps.demo.exception.ResourceNotFoundException;
import com.blps.demo.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping("/orders/payment")
    public AddPaymentResponse addPayment(@RequestBody AddPaymentRequest addPaymentRequest){
        Payment payment = paymentService.addPayment(addPaymentRequest.receive(),
                        addPaymentRequest.processor(),
                        addPaymentRequest.productOrderId());
        return new AddPaymentResponse(payment.getId(), payment.getReceive(), payment.getChange(), payment.getProcessor());
    }

    @GetMapping("/orders/payment/{orderId}")
    public GetPaymentResponse getPayment(@PathVariable Integer orderId){
        var payment = paymentService.getPayment(orderId);
        if (payment == null) {
            throw new ResourceNotFoundException("Данный заказ еще не был оплачен или не существует");
        }
        return new GetPaymentResponse(payment.getReceive(), payment.getChange(), payment.getProcessor());
    }

}

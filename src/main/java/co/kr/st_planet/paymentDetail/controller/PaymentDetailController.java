package co.kr.st_planet.paymentDetail.controller;

import co.kr.st_planet.paymentDetail.service.PaymentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paymentDetail")
public class PaymentDetailController {

    @Autowired
    private PaymentDetailService paymentDetailService;
}

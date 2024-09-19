package co.kr.st_planet.paymentDetail.service;

import co.kr.st_planet.paymentDetail.mapper.PaymentDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailService {
    @Autowired
    private PaymentDetailMapper paymentDetailMapper;

}

package co.kr.st_planet.coupon.service;

import co.kr.st_planet.coupon.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    @Autowired
    private CouponMapper couponMapper;
}

package co.kr.st_planet.ownedCoupon.service;

import co.kr.st_planet.ownedCoupon.mapper.OwnedCouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnedCouponService {

    @Autowired
    private OwnedCouponMapper ownedCouponMapper;
}

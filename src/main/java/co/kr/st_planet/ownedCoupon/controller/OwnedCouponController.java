package co.kr.st_planet.ownedCoupon.controller;

import co.kr.st_planet.ownedCoupon.service.OwnedCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ownedCoupon")
public class OwnedCouponController {

    @Autowired
    private OwnedCouponService ownedCouponService;
}

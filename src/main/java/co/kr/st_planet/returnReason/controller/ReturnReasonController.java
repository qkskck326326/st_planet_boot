package co.kr.st_planet.returnReason.controller;

import co.kr.st_planet.returnReason.service.ReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/returnReason")
public class ReturnReasonController {

    @Autowired
    private ReturnReasonService returnReasonService;
}

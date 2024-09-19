package co.kr.st_planet.returnProducts.controller;

import co.kr.st_planet.returnProducts.service.ReturnProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/returnProducts")
public class ReturnProductsController {

    @Autowired
    private ReturnProductsService returnProductsService;
}

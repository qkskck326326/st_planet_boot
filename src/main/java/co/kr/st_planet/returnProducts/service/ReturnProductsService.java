package co.kr.st_planet.returnProducts.service;

import co.kr.st_planet.returnProducts.mapper.ReturnProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnProductsService {

    @Autowired
    private ReturnProductsMapper returnProductsMapper;
}

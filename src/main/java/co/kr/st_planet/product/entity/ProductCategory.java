package co.kr.st_planet.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class ProductCategory {

    private int CategoryId;
    private String CategoryName;
}

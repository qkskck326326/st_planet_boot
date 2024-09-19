package co.kr.st_planet.returnImages.controller;

import co.kr.st_planet.returnImages.service.ReturnImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/returnImages")
public class ReturnImagesController {

    @Autowired
    private ReturnImagesService returnImagesService;
}

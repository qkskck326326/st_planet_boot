package co.kr.st_planet.returnImages.service;

import co.kr.st_planet.returnImages.mapper.ReturnImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnImagesService {
    @Autowired
    private ReturnImagesMapper returnImagesMapper;
}

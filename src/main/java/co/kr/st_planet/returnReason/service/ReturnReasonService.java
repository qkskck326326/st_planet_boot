package co.kr.st_planet.returnReason.service;

import co.kr.st_planet.returnReason.mapper.ReturnReasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnReasonService {

    @Autowired
    private ReturnReasonMapper returnReasonMapper;
}

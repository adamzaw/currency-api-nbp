package com.example.api_hw_week7.dto;

import com.example.api_hw_week7.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActualListMapper {

    @Autowired
    RateService rateService;

    public ActualListDto actualListDto() throws JsonProcessingException {

        ActualListDto actualListDto = new ActualListDto();
        actualListDto.actualList = rateService.getCurrencies()
                .get(0).getCurrencies().stream()
                .map(currency -> currency.getName() + ", code: " + currency.getCode())
                .collect(Collectors.toList());

        return actualListDto;
    }
}

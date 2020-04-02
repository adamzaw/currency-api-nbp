package com.example.api_hw_week7.dto;

import com.example.api_hw_week7.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListOfCurrenciesMapper {

    @Autowired
    private RateService rateService;

    public ListOfCurrenciesDto listOfCurrenciesToDto() throws JsonProcessingException {

        ListOfCurrenciesDto listOfCurrencesDto = new ListOfCurrenciesDto();
        listOfCurrencesDto.listOfCurrencies = rateService.getCurrencies().get(0);

        return listOfCurrencesDto;
    }

}

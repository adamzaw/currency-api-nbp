package com.example.api_hw_week7.dto;

import com.example.api_hw_week7.model.ExchangeRate;
import com.example.api_hw_week7.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeResultMapper {

    @Autowired
    private RateService rateService;

    public ExchangeResultDto exchangeResultToDto(Double value, String first, String second) throws JsonProcessingException {

        ExchangeResultDto exchangeResultDTO = new ExchangeResultDto();

        Double result = (value *
                rateService.getExchangeRateByCode(first).getRate().get(0).getBid()) /
                rateService.getExchangeRateByCode(second).getRate().get(0).getAsk();

        exchangeResultDTO.setResult(result);
        exchangeResultDTO.setCode(second);

        return exchangeResultDTO;
    }

}

package com.example.api_hw_week7.service;

import com.example.api_hw_week7.model.ExchangeRate;
import com.example.api_hw_week7.model.ListOfCurrencies;
import com.example.api_hw_week7.model.Query;
import com.example.api_hw_week7.repository.QueryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Setter
@Service
public class RateService {

    @Autowired
    private QueryRepository queryRepository;

    private List<ListOfCurrencies> currencies;


    private ExchangeRate exchangeRate;

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public RateService() {
    }

    public List<ListOfCurrencies> getCurrencies() throws JsonProcessingException {

        String url = "http://api.nbp.pl/api/exchangerates/tables/a/";
        String jsonStr = restTemplate.getForObject(url, String.class);

        currencies = mapper.readValue(jsonStr, new TypeReference<List<ListOfCurrencies>>() {
        });

        queryRepository.save(new Query(url));

        return currencies;
    }

    public ExchangeRate getExchangeRateByCode(String code) throws JsonProcessingException {

        String url = "http://api.nbp.pl/api/exchangerates/rates/c/"+code+"/today/?format=json";
        String jsonStr = restTemplate.getForObject(url, String.class);

        exchangeRate = mapper.readValue(jsonStr, new TypeReference<ExchangeRate>() {
        });

        queryRepository.save(new Query(url));
        return exchangeRate;
    }


}

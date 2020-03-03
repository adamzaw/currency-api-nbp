package com.example.api_hw_week7.repository;

import com.example.api_hw_week7.model.ListOfCurrences;
import com.example.api_hw_week7.model.Query;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RateRepository {

    @Autowired
    private QueryRepository queryRepository;

    private List<ListOfCurrences> currences;

    public RateRepository() throws JsonProcessingException {

    }

    public List<ListOfCurrences> getCurrences() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/a/", String.class);

        ObjectMapper mapper = new ObjectMapper();
        currences = mapper.readValue(jsonStr,new TypeReference<List<ListOfCurrences>>(){});

        return currences;
    }
}

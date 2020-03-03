package com.example.api_hw_week7.repository;

import com.example.api_hw_week7.model.ListOfCurrences;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Setter
@Repository
public class RateRepository {


    private String lastQuery;

    private List<ListOfCurrences> currences;
    String url = "http://api.nbp.pl/api/exchangerates/tables/a/";

    public RateRepository() {

    }

    public List<ListOfCurrences> getCurrences() throws JsonProcessingException {


        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        currences = mapper.readValue(jsonStr, new TypeReference<List<ListOfCurrences>>() {
        });

        lastQuery = "http://api.nbp.pl/api/exchangerates/tables/a/";

        return currences;
    }

}

package com.example.api_hw_week7.controllers;

import com.example.api_hw_week7.model.Currency;
import com.example.api_hw_week7.model.ListOfCurrences;
import com.example.api_hw_week7.model.Query;
import com.example.api_hw_week7.repository.QueryRepository;
import com.example.api_hw_week7.repository.RateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class FrontEndController {

    RateRepository rateRepository = new RateRepository();

    @Autowired
    private QueryRepository queryRepository;

    public FrontEndController() throws JsonProcessingException {
    }


    @GetMapping("/api/list")
    public List<String> list() throws JsonProcessingException {


        List<ListOfCurrences> currencesList = rateRepository.getCurrences();

        queryRepository.save(new Query("/api/list/"));
        queryRepository.save(new Query(rateRepository.getLastQuery()));


        return currencesList.get(0).getCurrencies().stream()
                .map(currency -> currency.getName() + ", code: " + currency.getCode())
                .collect(Collectors.toList());
    }

    @GetMapping("/api/exchange/{value}/{first}/{second}")
    public String exchange(@PathVariable Double value, @PathVariable String first, @PathVariable String second) throws JsonProcessingException {


        List<ListOfCurrences> currencesList = rateRepository.getCurrences();


        queryRepository.save(new Query("/api/exchange/" + value + "/" + first + "/" + second));


        List<Currency> list = currencesList.get(0).getCurrencies();
        Double firstValue = 0.00;
        Double secondValue = 0.00;
        if (!list.isEmpty()) {
            for (Currency currence : list) {
                if (currence.getCode().equalsIgnoreCase(first)) {
                    firstValue = currence.getMid();
                } else if (currence.getCode().equalsIgnoreCase(second)) {
                    secondValue = currence.getMid();
                }
            }
        }
        Double result = (Double.valueOf(value) * Double.valueOf(firstValue)) / Double.valueOf(secondValue);

        return "{\"" + second + "\": \"" + String.format("%.3f", result) + "\"}";
    }

    @GetMapping("/api/actuallist")
    public List<ListOfCurrences> actualList() throws JsonProcessingException {


        List<ListOfCurrences> currencesList = rateRepository.getCurrences();

        queryRepository.save(new Query("/api/list/"));
        queryRepository.save(new Query(rateRepository.getLastQuery()));


        return currencesList;

    }
}

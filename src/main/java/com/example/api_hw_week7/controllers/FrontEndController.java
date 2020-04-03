package com.example.api_hw_week7.controllers;

import com.example.api_hw_week7.dto.*;
import com.example.api_hw_week7.model.Query;
import com.example.api_hw_week7.repository.QueryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.*;

@Api(value = "", description = "")
@RestController
@CrossOrigin
public class FrontEndController {

    @Autowired
    private ExchangeResultMapper exchangeResultMapper;

    @Autowired
    private ListOfCurrenciesMapper listOfCurrencesMapper;

    @Autowired
    private ActualListMapper actualListMapper;


    @Autowired
    private QueryRepository queryRepository;

       public FrontEndController() throws JsonProcessingException {
    }


    @ApiImplicitParams(value = {})
    @ApiOperation(value = "Showing list of all currencies with rate")
    @GetMapping("/api/list")
    public ListOfCurrenciesDto list() throws JsonProcessingException {

        queryRepository.save(new Query("/api/list/"));

        return listOfCurrencesMapper.listOfCurrenciesToDto();
    }

    @ApiImplicitParams(value = {@ApiImplicitParam(name = "value", value = "Amount of money", dataType = "java.lang.Double"),
            @ApiImplicitParam(name = "first", value = "ISO 4217 currency code", dataType = "java.lang.String"),
            @ApiImplicitParam(name = "second", value = "ISO 4217 currency code", dataType = "java.lang.String")})
    @ApiOperation(value = "Calculating value of exchange from one currency to another")
    @GetMapping("/api/exchange/{value}/{first}/{second}")
    public ExchangeResultDto exchange(@PathVariable Double value, @PathVariable String first, @PathVariable String second) throws JsonProcessingException {

        queryRepository.save(new Query("/api/exchange/" + value + "/" + first + "/" + second));

        return exchangeResultMapper.exchangeResultToDto(value, first, second);
    }

    @ApiImplicitParams(value = {})
    @ApiOperation(value = "Showing list of all possible currencies")
    @GetMapping("/api/actuallist")
    public ActualListDto actualList() throws JsonProcessingException {

        queryRepository.save(new Query("/api/list/"));

        return actualListMapper.actualListDto();
    }
}

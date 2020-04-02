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
    @ApiOperation("")
    @GetMapping("/api/list")
    public ListOfCurrenciesDto list() throws JsonProcessingException {

        queryRepository.save(new Query("/api/list/"));

        return listOfCurrencesMapper.listOfCurrenciesToDto();
    }

    @ApiImplicitParams(value = {@ApiImplicitParam(name = "value", value = "", dataType = "java.lang.Double"),
            @ApiImplicitParam(name = "first", value = "", dataType = "java.lang.String"),
            @ApiImplicitParam(name = "second", value = "", dataType = "java.lang.String")})
    @ApiOperation("")
    @GetMapping("/api/exchange/{value}/{first}/{second}")
    public ExchangeResultDto exchange(@PathVariable Double value, @PathVariable String first, @PathVariable String second) throws JsonProcessingException {

        queryRepository.save(new Query("/api/exchange/" + value + "/" + first + "/" + second));

        return exchangeResultMapper.exchangeResultToDto(value, first, second);
    }

    @ApiImplicitParams(value = {})
    @ApiOperation("")
    @GetMapping("/api/actuallist")
    public ActualListDto actualList() throws JsonProcessingException {

        queryRepository.save(new Query("/api/list/"));

        return actualListMapper.actualListDto();
    }
}

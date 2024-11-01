package com.example.parcialAdnMutantes.controllers;

import com.example.parcialAdnMutantes.dto.StatsResponse;
import com.example.parcialAdnMutantes.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stats/")
public class StatsController {
    private StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    @GetMapping
    public StatsResponse getStats(){
        return statsService.getStats();
    }
}

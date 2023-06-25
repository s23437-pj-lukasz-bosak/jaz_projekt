package com.example.demo.controller;
import com.example.demo.service.GoldDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class GoldDataController {

    private final GoldDataService goldDataService;

    @Autowired
    public GoldDataController(GoldDataService goldDataService) {
        this.goldDataService = goldDataService;
    }

    @GetMapping("/averageGoldPrice")
    public BigDecimal getAverageGoldPrice(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return goldDataService.getAverageGoldPrice(LocalDate.parse(startDate), LocalDate.parse(endDate));
    }
}

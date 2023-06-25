package com.example.demo.service;

import com.example.demo.exceptions.GoldDataNotFoundException;
import com.example.demo.model.GoldData;
import com.example.demo.repository.GoldDataRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

@Service
public class GoldDataService {

    private final GoldDataRepository goldDataRepository;

    @Autowired
    public GoldDataService(GoldDataRepository goldDataRepository) {
        this.goldDataRepository = goldDataRepository;
    }

    public BigDecimal getAverageGoldPrice(LocalDate startDate, LocalDate endDate) {
        String url = "http://api.nbp.pl/api/cenyzlota/" + startDate + "/" + endDate;
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            BigDecimal sum = StreamSupport.stream(JsonParser.parseString(response.getBody()).getAsJsonArray().spliterator(), false)
                    .map(JsonObject.class::cast)
                    .map(jsonObject -> jsonObject.get("cena").getAsBigDecimal())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal averagePrice = sum.divide(BigDecimal.valueOf(JsonParser.parseString(response.getBody()).getAsJsonArray().size()), RoundingMode.HALF_UP);

            GoldData goldData = new GoldData();
            goldData.setStartDate(startDate);
            goldData.setEndDate(endDate);
            goldData.setAveragePrice(averagePrice);
            goldData.setQueryDateTime(LocalDateTime.now());
            goldDataRepository.save(goldData);

            return averagePrice;

        } catch (RestClientException ex) {
            throw new GoldDataNotFoundException("Gold price not found");
        }
    }

}

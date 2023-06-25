package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class GoldData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Metals metal;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal averagePrice;
    private LocalDateTime queryDateTime;

    public GoldData() {}

    public GoldData(LocalDate startDate, LocalDate endDate, BigDecimal averagePrice, LocalDateTime queryDateTime) {
        this.metal = Metals.GOLD;
        this.startDate = startDate;
        this.endDate = endDate;
        this.averagePrice = averagePrice;
        this.queryDateTime = queryDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Metals getMetal() {
        return metal;
    }

    public void setMetal(Metals metal) {
        this.metal = metal;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public LocalDateTime getQueryDateTime() {
        return queryDateTime;
    }

    public void setQueryDateTime(LocalDateTime queryDateTime) {
        this.queryDateTime = queryDateTime;
    }

    public enum Metals {
        GOLD
    }
}

package com.springflame.commissionapp.service.currency;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ExchangeRateRestClient {

    private final RestTemplate restTemplate;

    public ExchangeRateRestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // https://exchangerate.host/#/docs
    public BigDecimal getConvertedAmount(ConversionInput conversionInput) {
        String url = "(https://api.exchangerate.host/";
        return null;
    }
}
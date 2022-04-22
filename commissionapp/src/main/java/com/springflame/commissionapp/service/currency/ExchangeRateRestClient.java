package com.springflame.commissionapp.service.currency;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

@Service
public class ExchangeRateRestClient {

    private final RestTemplate restTemplate;

    public ExchangeRateRestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // https://exchangerate.host/#/docs
    public BigDecimal getConvertedAmount(ConversionInput conversionInput) {
        try {
            String originalAmountFormatted = BigDecimalFormatter.bigDecimalToDefaultPrecisionString(conversionInput.getSourceAmount());
            String url = String.format("https://api.exchangerate.host/convert?from=%s&to=%s&amount=%s&date=%s",
                conversionInput.getSourceCurrency(),
                conversionInput.getTargetCurrency(),
                originalAmountFormatted,
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(conversionInput.getDate()));

            String result = this.restTemplate.getForObject(url, String.class);

            JsonNode root = new ObjectMapper().readTree(result);
            String convertedAmount = root.at("/result").toString();
            return new BigDecimal(convertedAmount).setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) { // TODO make more fine-grained, do not catch everything
            throw new RuntimeException("Count not retrieve the exchange rate for" + conversionInput);
        }
    }
}
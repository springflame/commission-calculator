package com.springflame.commissionapp.service.currency;

import com.springflame.commissionapp.dto.MoneyAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CurrencyConverter {

	public static final String systemCurrency = "EUR";

	@Autowired
	private ExchangeRateRestClient exchangeRateRestClient;

	public MoneyAmount convertToSystemCurrency(BigDecimal amount, String currency, LocalDate date) {
		BigDecimal convertedAmount = exchangeRateRestClient.getConvertedAmount(
				ConversionInput.builder()
						.date(date)
						.sourceCurrency(currency)
						.sourceAmount(amount)
						.targetCurrency(systemCurrency)
						.build());
		return MoneyAmount.of(convertedAmount, systemCurrency);
	}
}

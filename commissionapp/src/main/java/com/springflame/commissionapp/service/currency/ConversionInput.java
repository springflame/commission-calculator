package com.springflame.commissionapp.service.currency;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ConversionInput {
	private BigDecimal sourceAmount;
	private String sourceCurrency;
	private String targetCurrency;
	private LocalDate date;
}

package com.springflame.commissionapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyAmount {

	private BigDecimal amount;
	private String currency;

	public static MoneyAmount of(BigDecimal amount, String currency) {
		MoneyAmount moneyAmount = new MoneyAmount();
		moneyAmount.setAmount(amount);
		moneyAmount.setCurrency(currency);
		return moneyAmount;
	}
}

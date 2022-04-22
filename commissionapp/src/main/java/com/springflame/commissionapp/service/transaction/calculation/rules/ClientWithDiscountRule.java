package com.springflame.commissionapp.service.transaction.calculation.rules;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.model.Transaction;
import com.springflame.commissionapp.service.currency.CurrencyConverter;
import org.apache.groovy.util.Maps;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
class ClientWithDiscountRule implements CommissionCalculationRule {

	private final Map<String, BigDecimal> clientsWithDiscounts = Maps.of("42", new BigDecimal("0.05"));

	@Override
	public boolean isApplicable(Transaction transaction) {
		return clientsWithDiscounts.containsKey(transaction.getClientId());
	}

	@Override
	public CommissionDTO apply(Transaction transaction) {
		return CommissionDTO.builder()
				.amount(clientsWithDiscounts.get(transaction.getClientId()))
				.currency(CurrencyConverter.systemCurrency)
				.build();
	}
}

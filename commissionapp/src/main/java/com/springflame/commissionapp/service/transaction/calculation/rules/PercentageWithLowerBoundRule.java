package com.springflame.commissionapp.service.transaction.calculation.rules;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.model.Transaction;
import com.springflame.commissionapp.service.currency.CurrencyConverter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
class PercentageWithLowerBoundRule implements CommissionCalculationRule {

	private final BigDecimal commissionRate = new BigDecimal("0.005");
	private final BigDecimal lowerBound = new BigDecimal("0.05");

	@Override
	public boolean isApplicable(Transaction transaction) {
		return true;
	}

	@Override
	public CommissionDTO apply(Transaction transaction) {
		BigDecimal commission = transaction.getAmountInSystemCurrency().multiply(commissionRate);
		if (commission.compareTo(lowerBound) < 0) {
			commission = lowerBound;
		}
		return CommissionDTO.builder()
				.amount(commission)
				.currency(CurrencyConverter.systemCurrency)
				.build();
	}
}

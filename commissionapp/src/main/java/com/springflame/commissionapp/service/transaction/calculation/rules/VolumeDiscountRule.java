package com.springflame.commissionapp.service.transaction.calculation.rules;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.model.Transaction;
import com.springflame.commissionapp.persistence.transaction.TransactionRepository;
import com.springflame.commissionapp.service.currency.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
class VolumeDiscountRule implements CommissionCalculationRule {

	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * Assuming we always operate in the system currency.
	 */
	private final BigDecimal volumeThreshold = new BigDecimal("1000");

	@Override
	public boolean isApplicable(Transaction transaction) {
		List<Transaction> allTransactionsInTheSameMonth = transactionRepository.findTransactionsByMonth(transaction)
				.stream()
				.filter(anotherTransaction -> !anotherTransaction.getId().equals(transaction.getId()))
				.collect(Collectors.toList());
		if (ObjectUtils.isEmpty(allTransactionsInTheSameMonth)) {
			return false;
		}
		return allTransactionsInTheSameMonth
				.stream()
				.map(Transaction::getAmountInSystemCurrency)
				.reduce(BigDecimal.valueOf(0), BigDecimal::add).compareTo(volumeThreshold) >= 0;

	}

	@Override
	public CommissionDTO apply(Transaction transaction) {
		return CommissionDTO.builder()
				.amount(new BigDecimal("0.03"))
				.currency(CurrencyConverter.systemCurrency)
				.build();
	}
}

package com.springflame.commissionapp.service.transaction.calculation.rules;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CommissionCalculator {

	@Autowired
	private List<CommissionCalculationRule> rules;

	public CommissionDTO calculateCommission(Transaction transaction) {
	return rules.stream()
			.filter(rule -> rule.isApplicable(transaction))
			.map(rule -> rule.apply(transaction))
			.min(Comparator.comparing(CommissionDTO::getAmount, Comparator.naturalOrder()))
			.orElseThrow(() -> new RuntimeException("Unable to find an applicable rule")); // TODO define exceptions
	}
}

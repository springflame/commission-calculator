package com.springflame.commissionapp.service.transaction;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.dto.MoneyAmount;
import com.springflame.commissionapp.dto.TransactionDTO;
import com.springflame.commissionapp.model.Transaction;
import com.springflame.commissionapp.persistence.transaction.TransactionRepository;
import com.springflame.commissionapp.service.transaction.calculation.rules.CommissionCalculator;
import com.springflame.commissionapp.service.currency.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CurrencyConverter currencyConverter;

	@Autowired
	private CommissionCalculator commissionCalculator;

	@Override
	public CommissionDTO saveAndCalculateCommission(TransactionDTO incomingTransaction) {
		MoneyAmount systemCurrencyAmount = currencyConverter.convertToSystemCurrency(incomingTransaction.getAmount(),
				incomingTransaction.getCurrency(), incomingTransaction.getDate());
		Transaction transactionToSave = mapToEntity(incomingTransaction, systemCurrencyAmount);
		transactionToSave = transactionRepository.save(transactionToSave);
		return commissionCalculator.calculateCommission(transactionToSave);
	}

	private Transaction mapToEntity(TransactionDTO transactionDTO, MoneyAmount systemCurrencyAmount) {
		Transaction transaction = new Transaction();
		transaction.setDate(transactionDTO.getDate());
		transaction.setClientId(transactionDTO.getClientId());
		transaction.setAmountInOriginalCurrency(transactionDTO.getAmount());
		transaction.setOriginalCurrency(transactionDTO.getCurrency());
		transaction.setAmountInSystemCurrency(systemCurrencyAmount.getAmount());
		transaction.setSystemCurrencyUsed(systemCurrencyAmount.getCurrency());
		return transaction;
	}

}

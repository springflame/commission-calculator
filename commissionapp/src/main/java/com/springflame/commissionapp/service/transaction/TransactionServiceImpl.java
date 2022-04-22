package com.springflame.commissionapp.service.transaction;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.dto.TransactionDTO;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Override
	public CommissionDTO saveAndCalculateCommission(TransactionDTO transaction) {
		return null;
	}
}

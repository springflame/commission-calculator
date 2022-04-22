package com.springflame.commissionapp.service.transaction;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.dto.TransactionDTO;

public interface TransactionService {

	CommissionDTO saveAndCalculateCommission(TransactionDTO transaction);
}

package com.springflame.commissionapp.service.transaction.calculation.rules;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.model.Transaction;

interface CommissionCalculationRule {

	boolean isApplicable(Transaction transaction);

	CommissionDTO apply(Transaction transaction);
}

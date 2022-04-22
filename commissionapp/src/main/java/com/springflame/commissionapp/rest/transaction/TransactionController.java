package com.springflame.commissionapp.rest.transaction;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.dto.TransactionDTO;
import com.springflame.commissionapp.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles all transaction-related requests.
 */
@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	/**
	 * Calculates the transaction fee in EUR and stores the transaction data
	 * to apply discounts in the future.
	 */
	@RequestMapping(value = "/api/transactions", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CommissionDTO> calculateCommission(@RequestBody TransactionDTO transaction) {
		return ResponseEntity.ok(transactionService.saveAndCalculateCommission(transaction));
	}
}
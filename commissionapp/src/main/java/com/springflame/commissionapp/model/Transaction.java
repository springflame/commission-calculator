package com.springflame.commissionapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private LocalDate date;

	// I would normally use javamoney,
	// but I had issues with mappings here
	// and for the purposes of this task it was faster to change types
	private BigDecimal amountInSystemCurrency;

	private String systemCurrencyUsed;

	private BigDecimal amountInOriginalCurrency;

	private String originalCurrency;

	private String clientId;
}

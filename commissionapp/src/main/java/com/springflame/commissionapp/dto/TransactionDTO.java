package com.springflame.commissionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDTO {

	@JsonProperty("date")
	private LocalDate date;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("client_id")
	private String clientId;
}

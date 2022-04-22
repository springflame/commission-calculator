package com.springflame.commissionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CommissionDTO {

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("currency")
	private String currency;

}

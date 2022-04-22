package com.springflame.commissionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springflame.commissionapp.serialization.BigDecimalSerializer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CommissionDTO {

	@JsonProperty("amount")
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal amount;

	@JsonProperty("currency")
	private String currency;

}

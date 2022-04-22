package com.springflame.commissionapp.mapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class JacksonObjectMapperConfiguration {

	@Autowired
	public void customize(ObjectMapper objectMapper) {
		objectMapper
				.registerModule(new JavaTimeModule())
				.configOverride(BigDecimal.class).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
	}
}

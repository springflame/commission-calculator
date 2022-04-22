package com.springflame.commissionapp.rest.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.springflame.commissionapp.data.TestData;
import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.dto.TransactionDTO;
import com.springflame.commissionapp.service.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Mock
	private TransactionService transactionService;

	@InjectMocks
	private TransactionController transactionController;

	@BeforeEach
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(transactionController)
				.build();
	}

	@Test
	public void shouldGetCommission() throws Exception {
		// given
		TransactionDTO mockTransaction = TestData.createTransaction();
		CommissionDTO commission = TestData.createCommission();
		when(transactionService.saveAndCalculateCommission(eq(mockTransaction))).thenReturn(commission);

		// when + then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(mockTransaction))
					.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(commission.getAmount()))
				.andExpect(jsonPath("$.currency").value(commission.getCurrency()));
		verify(transactionService).saveAndCalculateCommission(eq(mockTransaction));
	}
}

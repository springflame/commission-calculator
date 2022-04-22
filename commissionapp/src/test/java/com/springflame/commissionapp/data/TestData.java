package com.springflame.commissionapp.data;

import com.springflame.commissionapp.dto.CommissionDTO;
import com.springflame.commissionapp.dto.TransactionDTO;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestData {

	private static final TestCase client42WithDiscount = TestCase.of(
      "Client 42 (with discount)",

      "client-42-with-discount/1-request.json",
      "client-42-with-discount/1-response.json"
      );

	private static final TestCase currencyConversionDefaultHalfPercent = TestCase.of(
			"Currency conversion (rule chosen: default -- 0.5%)",

			"currency-conversion-default-half-percent/1-request.json",
			"currency-conversion-default-half-percent/1-response.json"
	);

	private static final TestCase currencyConversionHighTurnoverMultipleTransactions = TestCase.of(
			"Currency conversion (rule chosen: high turnover -- multiple transactions)",

			"currency-conversion-high-turnover-multiple-transactions/1-request.json",
			"currency-conversion-high-turnover-multiple-transactions/1-response.json",
			"currency-conversion-high-turnover-multiple-transactions/2-request.json",
			"currency-conversion-high-turnover-multiple-transactions/2-response.json",
			"currency-conversion-high-turnover-multiple-transactions/3-request.json",
			"currency-conversion-high-turnover-multiple-transactions/3-response.json"
	);

	private static final TestCase defaultHalfPercent = TestCase.of(
			"Default (0.5%)",

			"default-half-percent/1-request.json",
			"default-half-percent/1-response.json"
	);

	private static final TestCase defaultNotLessThan5Cents = TestCase.of(
			"Default (>= 0.05 EUR)",

			"default-not-less-than-5-cents/1-request.json",
			"default-not-less-than-5-cents/1-response.json"
	);

	private static final TestCase highTurnoverAppliedOnSecondTransaction = TestCase.of(
			"High turnover (applied on second transaction)",

			"high-turnover-applied-on-second-transaction/1-request.json",
			"high-turnover-applied-on-second-transaction/1-response.json",
			"high-turnover-applied-on-second-transaction/2-request.json",
			"high-turnover-applied-on-second-transaction/2-response.json"
	);

	private static final TestCase highTurnoverResetNextMonth = TestCase.of(
			"High turnover (reset next month)",

			"high-turnover-reset-next-month/1-request.json",
			"high-turnover-reset-next-month/1-response.json",
			"high-turnover-reset-next-month/2-request.json",
			"high-turnover-reset-next-month/2-response.json",
			"high-turnover-reset-next-month/3-request.json",
			"high-turnover-reset-next-month/3-response.json"
	);

	public static final List<TestCase> TEST_CASES = Lists.newArrayList(
			client42WithDiscount,
			currencyConversionDefaultHalfPercent,
			currencyConversionHighTurnoverMultipleTransactions,
			defaultHalfPercent,
			defaultNotLessThan5Cents,
			highTurnoverAppliedOnSecondTransaction,
			highTurnoverResetNextMonth
	);

	public static TransactionDTO createTransaction() {
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(new BigDecimal("2.00"));
		transactionDTO.setCurrency("USD");
		transactionDTO.setDate(LocalDate.ofYearDay(2021, 1));
		return transactionDTO;
	}

	public static CommissionDTO createCommission() {
		return CommissionDTO.builder()
				.amount(new BigDecimal("1.23"))
				.currency("EUR")
				.build();
	}
}

package com.springflame.commissionapp.integration;

import com.springflame.commissionapp.data.TestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionIT {
	private final static String BASE_URI = "http://localhost";
	private final static String REQUEST_PATH = "/api/transactions";

	@LocalServerPort
	private int port;

	@BeforeEach
	public void configureRestAssured() {
		RestAssured.baseURI = BASE_URI;
		RestAssured.port = port;
	}

	@ParameterizedTest(name = "''{0}''")
	@MethodSource("provideTestCases")
	public void shouldCalculateCommissionsForTransactions(String testCaseName, LinkedHashMap<String, String> requestResponsePairs) {
		for(Map.Entry<String, String> entry: requestResponsePairs.entrySet()) {
			Response response = RestAssured.given()
					.contentType(ContentType.JSON)
					.and()
					.body(entry.getKey())
					.when()
					.post(REQUEST_PATH)
					.then()
					.extract()
					.response();

			SoftAssertions softAssertions = new SoftAssertions();
			softAssertions.assertThat(response.statusCode())
					.as("Http status")
					.isEqualTo(HttpStatus.OK.value());
			softAssertions.assertThat(response.getBody().toString())
					.as("JSON response (commission)")
					.isEqualTo(entry.getValue());
			softAssertions.assertAll();
		}
	}

	private static Stream<Arguments> provideTestCases() {
		return TestData.TEST_CASES.stream()
						.map(testCase -> Arguments.of(testCase.getName(), testCase.getRequestResponsePairs()));
	}
}

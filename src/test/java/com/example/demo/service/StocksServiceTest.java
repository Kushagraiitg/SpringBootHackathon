package com.example.demo.service;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entities.Stocks;

import com.example.demo.repository.StocksRepository;

@ActiveProfiles("h2")
@SpringBootTest
public class StocksServiceTest {

	
	@Autowired
	StocksService stocksService;

	
	@MockBean
	StocksRepository stocksRepository;
	
	@Test
	public void testGetStocks() {
		int testId = 1;
		String testName = "testStocks";
		Stocks testStocks = new Stocks();
		testStocks.setId(testId);
		testStocks.setStockTicker(testName);
		
		when(stocksRepository.getStocksById(testStocks.getId()))
		.thenReturn(testStocks);
	
		Stocks returnedStocks = stocksService.getStocks(testId);
		
		assertThat(returnedStocks).isEqualTo(testStocks);
	}
}
	
	
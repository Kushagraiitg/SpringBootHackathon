/**
 * 
 */
package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entities.Stocks;
import com.example.demo.service.StocksService;

@ActiveProfiles("h2")
@SpringBootTest
public class MySQLStocksRepositoryTest {

	@Autowired
	MySQLStocksRepository mySQLStocksRepository;
		
	@Test
	public void testGetAllStocks() {
	
		List<Stocks> returnedList = mySQLStocksRepository.getAllStocks();
		
		
		assertThat(returnedList).isNotEmpty();
	}
	
	public void testGetPortfolioStocks() {
		
           List<Stocks> returnedList = mySQLStocksRepository.getPortfolioStocks();
		
		
		assertThat(returnedList).isNotEmpty();

		
	}
	
	@Test
	public void testGetStocksById() {
		Stocks s1 = new Stocks("AAPL",10, 1500, "BUY");
	    Stocks s2 = mySQLStocksRepository.addStocks(s1);
	    Stocks s3 =  mySQLStocksRepository.getStocksById(s2.getId());
	    assertThat(s2.getId()).isEqualTo(s3.getId());


	}
		
	//@Test
	//void givenStocksToAddShouldReturnAddedProduct() {
	     //stubbing
		//Stocks s1 = new Stocks("PQR",1500.99, 10, 'BUY', 0, 15009.90);
	    //when(StocksRepository.save(any())).thenReturn(s1);
	     //StocksService.addStocks(s1);
	     //verify(StocksRepository,times(1)).save(any());
	//}
	
		
}
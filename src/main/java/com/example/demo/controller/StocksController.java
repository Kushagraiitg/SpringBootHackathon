package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Stocks;
import com.example.demo.service.StocksService;

@CrossOrigin("*")
@RestController

@RequestMapping("api/stocks")
public class StocksController {

	@Autowired
	StocksService stockService;

	@GetMapping(value = "/")
	public List<Stocks> getAllStocks() {
		return stockService.getAllStocks();
	}
	
	@GetMapping(value = "/getTicker/{name}")
	public int getAllStocksNamed(@PathVariable("name")String name) {
		return stockService.getAllStocksNamed(name);
	}
	
	@GetMapping(value = "/sortby{name}")
	public List<Stocks> getAllStocks(@PathVariable("name")String name) {
		//System.out.println(name);
		return stockService.sortBy(name);
	}
	
	@GetMapping(value = "/portfolio")
	public List<Stocks> getPortfolioStocks() {
		return stockService.getPortfolioStocks();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Stocks> getStocksById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<Stocks>(stockService.getStocks(id), HttpStatus.OK);
		} catch(EmptyResultDataAccessException ex) {
			return new ResponseEntity<Stocks>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/")
	public Stocks addStocks(@RequestBody Stocks stocks) {
		return stockService.newStocks(stocks);
	}

	@PutMapping(value = "/")
	public Stocks editStocks(@RequestBody Stocks stocks) {
		return stockService.saveStocks(stocks);
	}

//	@DeleteMapping(value = "/{id}")
//	public int deleteStocks(@PathVariable int id) {
//		return Stockservice.deleteStocks(id);
//	}
	@GetMapping(value="/stkPrice/{stkName}")
	public float getStkValue(@PathVariable("stkName") String stkName) throws Exception {
//		System.out.println(Stockservice.getStkValue(stkName));
		return stockService.getStkValue(stkName);
	}
	
}
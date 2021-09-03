package com.example.demo.repository;

import java.util.List;
import com.example.demo.entities.Stocks;

public interface StocksRepository {
	public List<Stocks> getAllStocks();
	
	public List<Stocks> getPortfolioStocks();

	public Stocks getStocksById(int id);

	public Stocks editStocks(Stocks stocks);

	public int deleteStocks(int id);
	
	public int getStocksByName(String name);

	public Stocks addStocks(Stocks stocks);
	
	public List<Stocks> sortBy(String name);
	
}

package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.FetchStockData;
import com.example.demo.entities.Stocks;
import com.example.demo.repository.StocksRepository;;

@Service
public class StocksService {

	@Autowired
	private StocksRepository repository;

	public List<Stocks> getAllStocks() {
		return repository.getAllStocks();
	}
	

	public List<Stocks> getPortfolioStocks() {
		List<Stocks> temp=repository.getPortfolioStocks();
		HashMap<String,Integer> hash=new HashMap<String,Integer>();
		for(int i=0;i<temp.size();i++) {
			String name=temp.get(i).getStockTicker();
			if(hash.containsKey(name)) {
				int key=hash.get(name);
				if(temp.get(i).getBuyOrSell().equals("BUY")) {
				temp.get(key).setVolume(temp.get(key).getVolume()+temp.get(i).getVolume());
				temp.get(key).setTotalPrice(temp.get(key).getTotalPrice()+temp.get(i).getTotalPrice());
				temp.remove(i--);
				}
				else {
					temp.get(key).setVolume(temp.get(key).getVolume()-temp.get(i).getVolume());
					temp.get(key).setTotalPrice(temp.get(key).getTotalPrice()-temp.get(i).getTotalPrice());
					temp.remove(i--);
				}
				if(temp.get(key).getVolume()<=0) {
					temp.remove(key);
					i--;
					hash.remove(name);
					
				}
			}
			else {
				hash.put(name,i);
			}
		}
		
		
		
		return temp;
	}
		
	public List<Stocks> sortBy(String name){
		return repository.sortBy(name);

	}
	
	public int getAllStocksNamed(String name) {
		return repository.getStocksByName(name);
	}
	
	public Stocks getStocks(int id) {
		return repository.getStocksById(id);
	}

	public Stocks saveStocks(Stocks stocks) {
		return repository.editStocks(stocks);
	}

	public Stocks newStocks(Stocks stocks) {
		return repository.addStocks(stocks);
	}

//	public int deleteStocks(int id) {
//		return repository.deleteStocks(id);
//	}
	
	public float getStkValue(String stkName) throws Exception {
		//AlternativeStockPricing.findPrice(stkName);
		return FetchStockData.getStkValue(stkName);
		
	}
}
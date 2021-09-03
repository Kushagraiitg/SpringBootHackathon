package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Stocks;

@Repository
public class MySQLStocksRepository implements StocksRepository {

	@Autowired
	JdbcTemplate template;

	
	@Override
	public List<Stocks> sortBy(String name){
		String sql = "SELECT ID, stockTicker, price, volume, buyOrSell, statusCode,totalPrice, orderDate FROM Stocks ORDER BY "+name+" ;";
		return template.query(sql, new StocksRowMapper());
	}
	@Override
	public List<Stocks> getAllStocks() {
		// TODO Auto-generated method stub
		String sql = "SELECT ID, stockTicker, price, volume, buyOrSell, statusCode,totalPrice, orderDate FROM Stocks";
		return template.query(sql, new StocksRowMapper());
	}
	
	@Override
	public List<Stocks> getPortfolioStocks() {
		// TODO Auto-generated method stub
		String sql = "SELECT  stockTicker,  volume,  totalPrice,buyOrSell FROM Stocks WHERE statusCode=1 ";
		return template.query(sql, new newStocksRowMapper());
	}

	@Override
	public Stocks getStocksById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT id, stockTicker, price, volume, buyOrSell, statusCode,totalPrice,orderDate FROM Stocks "
				+ "WHERE id=?";
		return template.queryForObject(sql, new StocksRowMapper(), id);
	}
	
	@Override
	public int getStocksByName(String name){
		// TODO Auto-generated method stub
		String sql = "SELECT id, stockTicker, price, volume, buyOrSell, statusCode,totalPrice,orderDate FROM Stocks where  statusCode=1 ";
		List<Stocks> temp= template.query(sql, new StocksRowMapper());
		//System.out.println(temp.size());
		int res=0;
		for(int i=0;i<temp.size();i++) {
			if(temp.get(i).getStockTicker().equals(name)){
				if(temp.get(i).getBuyOrSell().equals("BUY")) {
					res=res+temp.get(i).getVolume();
				}
				else {
					res=res-temp.get(i).getVolume();
				}
			}
		}
		System.out.println("Volume is: "+res);
		return res;
	}
	
	
	@Override
	public Stocks editStocks(Stocks stocks) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Stocks SET stockTicker = ?, price = ?, volume = ?, buyOrSell = ?,totalPrice=?"
				+ " statusCode = ? WHERE id = ?";
		template.update(sql,stocks.getStockTicker(),stocks.getPrice(), stocks.getVolume(), stocks.getBuyOrSell(),stocks.getVolume()*stocks.getPrice(), stocks.getStatusCode(), stocks.getId());
		return stocks;
	}

	@Override
	public int deleteStocks(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Stocks WHERE id = ?";
		template.update(sql,id);
		return id;
	}

	@Override
	public Stocks addStocks(Stocks stocks) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Stocks(stockTicker, price, volume, buyOrSell, statusCode,totalPrice) " +
				"VALUES(?,?,?,?,?,?)";
		template.update(sql,stocks.getStockTicker(),stocks.getPrice(), stocks.getVolume(), stocks.getBuyOrSell(), stocks.getStatusCode(),stocks.getVolume()*stocks.getPrice());
		String sql1 = "SELECT ID, stockTicker, price, volume, buyOrSell, statusCode,orderDate,totalPrice FROM Stocks ORDER BY ID DESC LIMIT 1";
		return template.query(sql1, new StocksRowMapper()).get(0);
	}
}

class StocksRowMapper implements RowMapper<Stocks> {

	@Override
	public Stocks mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Stocks(rs.getInt("id"), rs.getString("stockTicker"), rs.getDouble("Price"), rs.getInt("volume"), rs.getString("buyOrSell"), rs.getInt("statusCode"),rs.getString("orderDate"),rs.getDouble("totalPrice"));
	}
	
}
class newStocksRowMapper implements RowMapper<Stocks> {

	@Override
	public Stocks mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Stocks(rs.getString("stockTicker"), rs.getInt("volume"),rs.getDouble("totalPrice"),rs.getString("buyOrSell"));
	}
	
}

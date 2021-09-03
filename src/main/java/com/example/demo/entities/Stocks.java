package com.example.demo.entities;

public class Stocks {
	private int id;
	private String stockTicker;
	private double price;
	private int volume;
	private String buyOrSell;
	private int statusCode;
	private String date;
	private double totalPrice;
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Stocks(int id, String stockTicker, double price, int volume, String buyOrSell, int statusCode,String date,double totalPrice) {
		super();
		this.id = id;
		this.stockTicker = stockTicker;
		this.price = price;
		this.volume = volume;
		this.buyOrSell = buyOrSell;
		this.statusCode = statusCode;
		this.date=date;
		this.totalPrice=totalPrice;
	}
	public Stocks(String stockTicker, int volume,double totalPrice,String buyOrSell) {
		super();
//		this.id = id;
		this.stockTicker = stockTicker;
//		this.price = price;
		this.volume = volume;
		this.buyOrSell = buyOrSell;
//		this.statusCode = statusCode;
//		this.date=date;
		this.totalPrice=totalPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStockTicker() {
		return stockTicker;
	}
	public void setStockTicker(String stockTicker) {
		this.stockTicker = stockTicker;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public Stocks() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getBuyOrSell() {
		return buyOrSell;
	}
	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}

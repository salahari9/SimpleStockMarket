package com.jpmorgan.stockmarket.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.Trade;
import com.jpmorgan.stockmarket.model.TradeType;
import com.jpmorgan.stockmarket.service.StockService;
import com.jpmorgan.stockmarket.service.TradeService;

/**
 * 
 * @author sureshbabualahari
 *
 */
public class StockUtil {
	
	/**
	 * This methid round of the double value
	 * @param value
	 * @param places
	 * @return round of Value 
	 */
	public static double roundOf(double value, int places) {
		BigDecimal bd = new BigDecimal(value).setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * This method just print the input value
	 * @param output
	 */
	public static void displayOutput(String output) {
		System.out.println("-------------------------------------");
		System.out.println(output);
		System.out.println("-------------------------------------");
	}

	/**
	 * This methiod reads the stock and stock price and invoke Stock service to get the Div Yield Value
	 * @param stock
	 * @param price
	 * @return Div Yield Value
	 */
	public static double calculateDividendYield(Stock stock, double price) {
		return StockService.getInstance().calculateDivYield(stock, price);
	}

	/**
	 * This method reads the stock and stock price and invoke Stock service to get the PB Ratio value
	 * @param stock
	 * @param price
	 * @return PB Ratio Value
	 */
	
	public static double calculatePERatio(Stock stock, double price) {
		return StockService.getInstance().calculatePERatio(stock, price);
	}

	/**
	 * This method reads the stock and invoke Stock service to get the Volume Weighted Stock Price Value
	 * @param stock
	 * @return olume Weighted Stock Price Value
	 */
	public static double calculateVolWeightedStockPrice(Stock stock) {
		List<Trade> trades = TradeService.getInstance().getTrades(stock, 15);
		return (trades != null && !trades.isEmpty()) ? StockService.getInstance().calVolWeightedStockPrice(trades) : 0;
	}

	/**
	 * This method reads the stock and invoke Stock service to get GBCE Value
	 * @return GBCE Value
	 */
	public static double calculateGBCE() {
		List<Trade> trades = TradeService.getInstance().getAllTrades();
		return (trades != null && !trades.isEmpty()) ? StockService.getInstance().calculateGBCE(trades) : 0;
	}

	/**
	 * This methd reads the Stock and Invoke Trade Service to store the trades.
	 * @param stock
	 * @param quantity
	 * @param type
	 * @param price
	 */
	public static void recordTable(Stock stock, int quantity, TradeType type, double price) {

		TradeService.getInstance()
				.recordTrade(new Trade(stock, Calendar.getInstance().getTime(), quantity, type, price));
	}
}

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

	public static double roundOf(double value, int places) {
		BigDecimal bd = new BigDecimal(value).setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static void displayOutput(String output) {
		System.out.println("-------------------------------------");
		System.out.println(output);
		System.out.println("-------------------------------------");
	}

	public static double calculateDividendYield(Stock stock, double price) {
		return StockService.getInstance().calculateDivYield(stock, price);
	}

	public static double calculatePERatio(Stock stock, double price) {
		return StockService.getInstance().calculatePERatio(stock, price);
	}

	public static double calculateVolWeightedStockPrice(Stock stock) {
		List<Trade> trades = TradeService.getInstance().getTrades(stock, 15);
		return (trades != null && !trades.isEmpty()) ? StockService.getInstance().calVolWeightedStockPrice(trades) : 0;
	}

	public static double calculateGBCE() {
		List<Trade> trades = TradeService.getInstance().getAllTrades();
		return (trades != null && !trades.isEmpty()) ? StockService.getInstance().calculateGBCE(trades) : 0;
	}

	public static void recordTable(Stock stock, int quantity, TradeType type, double price) {

		TradeService.getInstance()
				.recordTrade(new Trade(stock, Calendar.getInstance().getTime(), quantity, type, price));
	}
}

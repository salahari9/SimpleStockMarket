/**
 * 
 */
package com.jpmorgan.stockmarket.service;

import java.util.List;

import com.jpmorgan.stockmarket.dao.StockDao;
import com.jpmorgan.stockmarket.dao.impl.StockDaoImpl;
import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.StockType;
import com.jpmorgan.stockmarket.model.Trade;
import com.jpmorgan.stockmarket.util.StockUtil;

/**
 * @author sureshbabualahari
 *
 */
public class StockService {

	// Creating Singleton, which creates only one instance all the time.
	private static StockService stockService = null;
	private StockDao stockDao = new StockDaoImpl();

	public static StockService getInstance() {
		if (stockService == null) {
			stockService = new StockService();
		}
		return stockService;
	}

	public void addStock(Stock stock) {
		stockDao.addStock(stock);
	}

	public Stock getStock(String symbol) {
		return stockDao.getStock(symbol);
	}
	
	/**
	 * 
	 * @param stock
	 * @param price
	 * @return
	 */

	public double calculateDivYield(Stock stock, double price) {
		double divYieldVal =0;
		if(price > 0) {
			if (StockType.PREFERRED.equals(stock.getType())) {
				return ((stock.getFixedDividend()/100) * stock.getParValue()) / price;
			}
			double result = stock.getLastDividend() / price;
			divYieldVal = StockUtil.roundOf(result, 2);
		}
		
		return divYieldVal;
	}

	/**
	 * 
	 * @param stock
	 * @param price
	 * @return
	 */
	public double calculatePERatio(Stock stock, double price) {
		double peRatioVal =0;
		if(stock.getLastDividend() > 0) {
			double result = price / stock.getLastDividend();
			peRatioVal = StockUtil.roundOf(result, 2);
		}
		return peRatioVal;
	}
	
	/**
	 * 
	 * @param trades
	 * @return
	 */

	public double calVolWeightedStockPrice(List<Trade> trades) {
		double sumOfPriceQuantity = trades.stream().mapToDouble((trade) -> trade.getPrice() * trade.getQuantity())
				.sum();
		int sumOfQuantity = trades.stream().mapToInt((trade) -> trade.getQuantity()).sum();
		double result = sumOfPriceQuantity / sumOfQuantity;
		return StockUtil.roundOf(result, 2);
	}

	/**
	 * 
	 * @param trades
	 * @return
	 */
	public double calculateGBCE(List<Trade> trades) {
		double total = trades.stream().mapToDouble(trade -> trade.getPrice()).sum();
		double result = Math.pow(total, (1D / trades.size()));
		return StockUtil.roundOf(result, 2);
	}

}

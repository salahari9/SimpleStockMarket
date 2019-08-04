package com.jpmorgan.stockmarket.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.stockmarket.dao.StockDao;
import com.jpmorgan.stockmarket.model.Stock;

/**
 * 
 * @author sureshbabualahari
 *
 */

public class StockDaoImpl implements StockDao{
	
	private Map<String, Stock> stockMap = new HashMap<String, Stock>();

	@Override
	public void addStock(Stock stock) {
		stockMap.put(stock.getSymbol(),stock);
		
	}

	@Override
	public Stock getStock(String symbol) {
		return stockMap.get(symbol);
	}

}

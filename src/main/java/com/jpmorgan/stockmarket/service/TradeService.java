/**
 * 
 */
package com.jpmorgan.stockmarket.service;

import java.util.List;

import com.jpmorgan.stockmarket.dao.TradeDao;
import com.jpmorgan.stockmarket.dao.impl.TradeDaoImpl;
import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.Trade;

/**
 * @author sureshbabualahari
 *
 */
public class TradeService {
	
	private static TradeService tradeService = null;
	private TradeDao tradeDao = new TradeDaoImpl();
	
	// Making Singleton, which returns always single instance
	public static TradeService getInstance() {
		if(null == tradeService) {
			tradeService = new TradeService();
		}
		return tradeService;
	}
	/**
	 * This method store the trade in temp memory
	 * @param trade
	 */
	public void recordTrade(Trade trade) {
		if(null!=trade && null!= trade.getStock())
			tradeDao.addTrade(trade);
	}

	/**
	 * This nethod gives all the trades based on the recorded time
	 * @param stock
	 * @param min
	 * @return List of Trades
	 */
	public List<Trade> getTrades(Stock stock, int min){
		return tradeDao.getTrades(stock, min);
	}
	
	/**
	 * This method returns all the trades
	 * @return all trades
	 */
	public List<Trade> getAllTrades(){
		
		return tradeDao.getAllTrades();
	}
}

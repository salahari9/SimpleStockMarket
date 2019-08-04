/**
 * 
 */
package com.jpmorgan.stockmarket.dao;

import java.util.List;

import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.Trade;

/**
 * @author sureshbabualahari
 *
 */
public interface TradeDao {

	/**
	 * 
	 * @param trade
	 */
	void addTrade(Trade trade);
	
	/**
	 * 
	 * @param stock
	 * @param minutes
	 * @return
	 */
	List<Trade> getTrades(Stock stock, int minutes);
	
	/**
	 * 
	 * @return
	 */
	List<Trade> getAllTrades();
}

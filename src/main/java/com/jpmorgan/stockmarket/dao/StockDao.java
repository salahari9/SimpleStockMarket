/**
 * 
 */
package com.jpmorgan.stockmarket.dao;

import com.jpmorgan.stockmarket.model.Stock;

/**
 * @author sureshbabualahari
 *
 */
public interface StockDao {

	/**
	 * 
	 * @param stock
	 */
	void addStock(Stock stock);
	
	/**
	 * 
	 * @param symbol
	 * @return
	 */
	Stock getStock(String symbol);
	
}

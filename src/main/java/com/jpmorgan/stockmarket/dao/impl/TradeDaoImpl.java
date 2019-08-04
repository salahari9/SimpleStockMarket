/**
 * 
 */
package com.jpmorgan.stockmarket.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jpmorgan.stockmarket.dao.TradeDao;
import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.Trade;

/**
 * @author sureshbabualahari
 *
 */
public class TradeDaoImpl implements TradeDao{

	private Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>();
	
	@Override
	public void addTrade(Trade trade) {
		// TODO Auto-generated method stub
		List<Trade> trades = new ArrayList<Trade>();
	    if (tradeMap.containsKey(trade.getStock().getSymbol())) {
	        trades = tradeMap.get(trade.getStock().getSymbol());
	    }
	    trades.add(trade);
	    tradeMap.put(trade.getStock().getSymbol(), trades);
		
	}

	@Override
	public List<Trade> getTrades(Stock stock, int minutes) {
		// TODO Auto-generated method stub
		List<Trade> result = new ArrayList<Trade>();	    
	    List<Trade> tradeList = tradeMap.get(stock.getSymbol());
	    // Get the Latest TradesFirst
	    if(null != tradeList) {
	    	Date afterDate = getDateInMinutes(minutes);
	    	Collections.sort(tradeList);
		    Iterator<Trade> it = tradeList.iterator();
		    while(it.hasNext()) {
		    	Trade trade = it.next();
		    	if(trade.getTimestamp().before(afterDate)) {
		    		break;
		    	}
		    	result.add(trade);
		    }
	    }
	    
		return result;
	}

	@Override
	public List<Trade> getAllTrades() {
		// TODO Auto-generated method stub
		List<Trade> result = new ArrayList<Trade>();
	    for (String stockSymbol: tradeMap.keySet()) {
	      result.addAll(tradeMap.get(stockSymbol));
	    }
	    return result;
	}

	private Date getDateInMinutes(int min) {
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.MINUTE, -min);
	    return c.getTime();
	}
}

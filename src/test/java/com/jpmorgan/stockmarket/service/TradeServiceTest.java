package com.jpmorgan.stockmarket.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.StockType;
import com.jpmorgan.stockmarket.model.Trade;
import com.jpmorgan.stockmarket.model.TradeType;
import com.jpmorgan.stockmarket.service.TradeService;

public class TradeServiceTest {

	private Stock abcStock;
	private TradeService tradeService = TradeService.getInstance();
	List<Trade> trades = new ArrayList<Trade>();

	@Before
	public void setup() {
		abcStock = new Stock("ABC", StockType.COMMON, 4, 0, 10);
	}

	@Test
	public void testAllTrades() {
		tradeService.recordTrade(new Trade(abcStock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
		tradeService.recordTrade(new Trade(abcStock, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
		tradeService.recordTrade(new Trade(abcStock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
		List<Trade> result = tradeService.getAllTrades();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testRecordTrade() {
		Trade trade = new Trade(abcStock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 1);
		tradeService.recordTrade(trade);
		List<Trade> result = tradeService.getTrades(abcStock, 15);
		assertNotNull(result);
	}
}

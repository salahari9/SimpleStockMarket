package com.jpmorgan.stockmarket.stockmarket.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.StockType;
import com.jpmorgan.stockmarket.model.Trade;
import com.jpmorgan.stockmarket.model.TradeType;
import com.jpmorgan.stockmarket.service.StockService;

/**
 * 
 * @author sureshbabualahari
 *
 */
public class StockServiceTest {
	
	private Stock abcStock;
	private Stock xyzStock;
	private StockService stockService = StockService.getInstance();
	List<Trade> trades = new ArrayList<Trade>();
	
	@Before
	public void setup() {
		abcStock = new Stock("ABC", StockType.COMMON, 4, 0, 10);
		xyzStock = new Stock("XYZ", StockType.PREFERRED, 3,2,1);
	    trades.add(new Trade(abcStock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
	    trades.add(new Trade(abcStock, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
	    trades.add(new Trade(abcStock, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
	}

	@Test
	public void testStock() {
		stockService.addStock(abcStock);
		Stock tempStock = stockService.getStock(abcStock.getSymbol());
		assertEquals(abcStock, tempStock);
	}
	
	@Test
	public void testCalculateDivYield() {
		assertEquals(1.6, stockService.calculateDivYield(abcStock, 2.5),0);
	}
	
	@Test
	public void testCalculateDivYieldPreferredType() {
		assertEquals(0.008, stockService.calculateDivYield(xyzStock, 2.5),0);
	}
	
	@Test
	public void testCalculatePERatio() {
		assertEquals(0.63, stockService.calculatePERatio(abcStock, 2.5),0);
	}
	
	@Test
	public void testCalVolWeightedStockPrice() {
	    assertEquals(1.9, stockService.calVolWeightedStockPrice(trades), 0);
	}
	
	@Test
	public void testCalculateGBCE() {
		assertEquals(1.87, stockService.calculateGBCE(trades), 0);
	}
	
}

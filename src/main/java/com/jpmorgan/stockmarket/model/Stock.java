/**
 * 
 */
package com.jpmorgan.stockmarket.model;


/**
 * @author sureshbabualahari
 *
 */


public class Stock {

	private String symbol;
	private StockType type;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;
	
	/**
	 * 
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 */
	
	public Stock(String symbol, StockType type, double lastDividend, double fixedDividend, double parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the type
	 */
	public StockType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(StockType type) {
		this.type = type;
	}
	/**
	 * @return the lastDividend
	 */
	public double getLastDividend() {
		return lastDividend;
	}
	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	/**
	 * @return the fixedDividend
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}
	/**
	 * @param fixedDividend the fixedDividend to set
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	/**
	 * @return the parValue
	 */
	public double getParValue() {
		return parValue;
	}
	/**
	 * @param parValue the parValue to set
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	
}

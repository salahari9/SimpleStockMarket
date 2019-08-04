package com.jpmorgan.stockmarket;

import java.util.Scanner;

import com.jpmorgan.stockmarket.exception.StockMarketException;
import com.jpmorgan.stockmarket.model.Stock;
import com.jpmorgan.stockmarket.model.StockType;
import com.jpmorgan.stockmarket.model.TradeType;
import com.jpmorgan.stockmarket.service.StockService;
import com.jpmorgan.stockmarket.util.StockUtil;

public class StockMarketApp {

	private static Scanner scanner;

	public static void main(String[] args) {

		initializeDummyData();
		displayMainMenuOptions();

		scanner = new Scanner(System.in);
		String choice = null;
		while (true) {
			choice = scanner.nextLine();
			if ("q".equals(choice)) {
				scanner.close();
				System.exit(0);
			} else {
				try {
					int option = Integer.parseInt(choice);
					Stock stock;
					switch (option) {
					case 1:
						stock = getStock();
						invokeSubMenu(stock);
					case 2:
						double gbce = StockUtil.calculateGBCE();
						if (gbce != 0) {
							StockUtil.displayOutput("GBCE: " + gbce);
						} else {
							StockUtil.displayOutput("No Trades Found");
						}
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
					StockUtil.displayOutput("Invalid Option");
				} catch (StockMarketException e1) {
					StockUtil.displayOutput(e1.getMessage());
				}
				System.out.println("");
				displayMainMenuOptions();
			}
		}

	}

	/**
	 * @param stock
	 */
	private static void invokeSubMenu(Stock stock) {
		String choice;
		int option;
		double stockPrice;
		displaySubMenuOptions();
		while (true) {
			
			choice = scanner.nextLine();
			if ("q".equals(choice)) {
				scanner.close();
				System.exit(0);
			}else if ("0".equals(choice)) {
				break;
			} else {
				try {
					option = Integer.parseInt(choice);
					switch (option) {
					case 1:
						stockPrice = getStockPrice();
						double divYield = StockUtil.calculateDividendYield(stock, stockPrice);
						StockUtil.displayOutput("Dividend Yield: " + divYield);
						break;
					case 2:
						stockPrice = getStockPrice();
						double peRation = StockUtil.calculatePERatio(stock, stockPrice);
						StockUtil.displayOutput("PERatio: " + peRation);
						break;
					case 3:
						stock = getStock();
						int quantityFromUser = getQuantity();
						TradeType tradeTypeFromUser = getTradeType();
						stockPrice = getStockPrice();
						StockUtil.recordTable(stock, quantityFromUser, tradeTypeFromUser, stockPrice);
						StockUtil.displayOutput("Trade recorded succcessfully");
						break;
					case 4:
						double volWeighted = StockUtil.calculateVolWeightedStockPrice(stock);
						StockUtil.displayOutput("Volume Weighted Stock Price: " + volWeighted);
						break;
					case 0:
						displayMainMenuOptions();
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
					StockUtil.displayOutput("Invalid Option");
				}catch (StockMarketException e1) {
					StockUtil.displayOutput(e1.getMessage());
				}
				System.out.println("");
				displaySubMenuOptions();
			}

		}
	}

	private static void initializeDummyData() {
		
		StockService stockService = StockService.getInstance();

		stockService.addStock(new Stock("TEA", StockType.COMMON, 0, 0, 100));
		stockService.addStock(new Stock("POP", StockType.COMMON, 8, 0, 100));
		stockService.addStock(new Stock("ALE", StockType.COMMON, 23, 0, 60));
		stockService.addStock(new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
		stockService.addStock(new Stock("JOE", StockType.COMMON, 13, 0, 250));

	}

	private static Stock getStock() throws StockMarketException {
		System.out.println("Please enter stock symbol");
		String stockSymbol = scanner.nextLine();
		Stock stock = StockService.getInstance().getStock(stockSymbol);
		if (stock == null) {
			throw new StockMarketException("Stock not found");
		}
		return stock;
	}

	private static double getStockPrice() throws StockMarketException {
		System.out.println("Please enter stock price");
		String stockPrice = scanner.nextLine();
		try {
			double result = Double.parseDouble(stockPrice);
			if (result <= 0) {
				throw new StockMarketException("Invalid price: It Must be greater than 0");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new StockMarketException("Invalid price: It is Not a number");
		}
	}

	private static TradeType getTradeType() throws StockMarketException {
		System.out.println("Please enter trade type (BUY/SELL)");
		String type = scanner.nextLine();
		try {
			return TradeType.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new StockMarketException("Invalid trade type: It Must be BUY or SELL");
		}
	}

	private static int getQuantity() throws StockMarketException {
		System.out.println("Please input quantity");
		String quantity = scanner.nextLine();
		try {
			int result = Integer.parseInt(quantity);
			if (result <= 0) {
				throw new StockMarketException("Invalid quantity: It Must be greater than 0");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new StockMarketException("Invalid quantity: Not a number");
		}
	}

	private static void displayMainMenuOptions() {
		System.out.println("JPMorgan - Super simple stock market");
		System.out.println("1: Please enter stock symbol");
		System.out.println("2: Calculate GBCE All Share Index");
		System.out.println("q: Quit");
	}

	private static void displaySubMenuOptions() {
		System.out.println("JPMorgan - Super simple stock market");
		System.out.println("1: Calculate dividend yield for stock");
		System.out.println("2: Calculate P/E ratio for stock");
		System.out.println("3: Record a trade for stock");
		System.out.println("4: Calculate Volume Weighted Stock Price for stock");
		System.out.println("0: Back to Previous Options");
		System.out.println("q: Quit");
	}
}

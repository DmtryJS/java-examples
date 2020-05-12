package com.mageddo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mageddo.domain.Stock;

//@Singleton
public class StockPriceDaoMemory implements StockPriceDao {

  private Map<String, Stock> stocks = new LinkedHashMap<>();

  @Override
  public void updateStockPrice(Stock stock) {
    this.stocks.put(stock.getSymbol(), stock);
  }

  @Override
  public Stock getStock(String symbol){
    return this.stocks.get(symbol);
  }

  @Override
  public void createStock(Stock stock) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Stock> find() {
    throw new UnsupportedOperationException();
  }
}

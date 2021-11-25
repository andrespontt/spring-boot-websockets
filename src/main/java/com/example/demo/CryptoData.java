package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CryptoData {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

class Data {
    @JsonProperty("market_data")
    private MarketData marketData;

    public MarketData getMarketData() {
        return marketData;
    }

    public void setMarketData(MarketData marketData) {
        this.marketData = marketData;
    }

}

class MarketData {
    
    @JsonProperty("price_usd")
    private String priceUsd;

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

}
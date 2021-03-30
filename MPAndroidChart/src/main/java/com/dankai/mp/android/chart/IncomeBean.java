package com.dankai.mp.android.chart;

public class IncomeBean {
    private String tradeDate;
    private double value;

    public IncomeBean(String tradeDate, double value) {
        this.tradeDate = tradeDate;
        this.value = value;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

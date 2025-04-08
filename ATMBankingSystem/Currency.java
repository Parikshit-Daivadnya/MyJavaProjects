package ATMBankingSystem;

import java.util.HashMap;

public class Currency {
    private String name;
    private HashMap<String, Double> exchangeValues;

    public Currency(String name) {
        this.name = name;
        this.exchangeValues = new HashMap<>();
    }

    public void addExchangeRate(String toCurrency, double rate) {
        this.exchangeValues.put(toCurrency, rate);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Double> getExchangeValues() {
        return exchangeValues;
    }
}

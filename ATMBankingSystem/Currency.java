package ATMBankingSystem;

import java.util.ArrayList;
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

    // ✅ Static init method to create sample currencies
    public static ArrayList<Currency> init() {
        ArrayList<Currency> currencies = new ArrayList<>();

        Currency usd = new Currency("USD");
        Currency inr = new Currency("INR");
        Currency eur = new Currency("EUR");

        // Add exchange rates
        usd.addExchangeRate("INR", 83.2);
        usd.addExchangeRate("EUR", 0.92);
        usd.addExchangeRate("USD", 1.0);

        inr.addExchangeRate("USD", 0.012);
        inr.addExchangeRate("EUR", 0.011);
        inr.addExchangeRate("INR", 1.0);

        eur.addExchangeRate("USD", 1.09);
        eur.addExchangeRate("INR", 90.1);
        eur.addExchangeRate("EUR", 1.0);

        currencies.add(usd);
        currencies.add(inr);
        currencies.add(eur);

        return currencies;
    }
}

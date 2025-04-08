package ATMBankingSystem;

import java.util.ArrayList;

public class ATM {
    public static ArrayList<Currency> init() {
        ArrayList<Currency> currencies = new ArrayList<>();

        Currency inr = new Currency("Indian Rupee");
        Currency usd = new Currency("US Dollar");
        Currency eur = new Currency("Euro");
        Currency jpy = new Currency("Japanese Yen");

        inr.addExchangeRate("US Dollar", 0.012);
        inr.addExchangeRate("Euro", 0.011);
        inr.addExchangeRate("Japanese Yen", 1.76);

        usd.addExchangeRate("Indian Rupee", 82.74);
        usd.addExchangeRate("Euro", 0.92);
        usd.addExchangeRate("Japanese Yen", 145.67);

        eur.addExchangeRate("Indian Rupee", 90.12);
        eur.addExchangeRate("US Dollar", 1.09);
        eur.addExchangeRate("Japanese Yen", 158.27);

        jpy.addExchangeRate("Indian Rupee", 0.57);
        jpy.addExchangeRate("US Dollar", 0.0069);
        jpy.addExchangeRate("Euro", 0.0063);

        currencies.add(inr);
        currencies.add(usd);
        currencies.add(eur);
        currencies.add(jpy);

        return currencies;
    }

    public static double convert(double amount, double rate) {
        return amount * rate;
    }

    public static String getName(Currency currency) {
        return currency.getName();
    }
}

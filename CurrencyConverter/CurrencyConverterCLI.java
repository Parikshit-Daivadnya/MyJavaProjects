package CurrencyConverter;

import ATMBankingSystem.ATM;
import ATMBankingSystem.Currency;

import java.util.ArrayList;
import java.util.Scanner;

public class CurrencyConverterCLI {
    public static void main(String[] args) {
        ArrayList<Currency> currencies = ATM.init();
        Scanner sc = new Scanner(System.in);

        System.out.println("Available Currencies:");
        for (int i = 0; i < currencies.size(); i++) {
            System.out.println(i + 1 + ". " + currencies.get(i).getName());
        }

        System.out.print("From Currency (1-" + currencies.size() + "): ");
        int from = sc.nextInt() - 1;
        System.out.print("To Currency (1-" + currencies.size() + "): ");
        int to = sc.nextInt() - 1;
        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        Currency fromCurr = currencies.get(from);
        Currency toCurr = currencies.get(to);
        double rate = fromCurr.getExchangeValues().get(toCurr.getName());
        double result = ATM.convert(amt, rate);

        System.out.printf("Converted Amount: %.2f %s\n", result, toCurr.getName());
    }
}

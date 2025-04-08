package CurrencyConverter;

import ATMBankingSystem.ATM;
import ATMBankingSystem.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CurrencyConverterGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        ArrayList<Currency> currencies = ATM.init();
        String[] currencyNames = currencies.stream().map(Currency::getName).toArray(String[]::new);

        JComboBox<String> fromBox = new JComboBox<>(currencyNames);
        JComboBox<String> toBox = new JComboBox<>(currencyNames);
        JTextField amountField = new JTextField();
        JLabel resultLabel = new JLabel("Converted Amount: ");
        JButton convertBtn = new JButton("Convert");

        frame.add(new JLabel("From Currency:"));
        frame.add(fromBox);
        frame.add(new JLabel("To Currency:"));
        frame.add(toBox);
        frame.add(new JLabel("Amount:"));
        frame.add(amountField);
        frame.add(new JLabel(""));
        frame.add(convertBtn);
        frame.add(new JLabel(""));
        frame.add(resultLabel);

        convertBtn.addActionListener(e -> {
            try {
                int fromIndex = fromBox.getSelectedIndex();
                int toIndex = toBox.getSelectedIndex();
                double amount = Double.parseDouble(amountField.getText());

                Currency from = currencies.get(fromIndex);
                Currency to = currencies.get(toIndex);

                double rate = from.getExchangeValues().get(to.getName());
                double converted = ATM.convert(amount, rate);
                resultLabel.setText("Converted Amount: " + String.format("%.2f", converted) + " " + to.getName());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}

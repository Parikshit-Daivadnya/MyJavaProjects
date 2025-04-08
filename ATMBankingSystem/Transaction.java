package ATMBankingSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private double amount;
    private Date timestamp;
    private String memo;

    public Transaction(double amount, String memo) {
        this.amount = amount;
        this.memo = memo;
        this.timestamp = new Date();
    }

    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateStr = sdf.format(this.timestamp);

        String type = (amount >= 0) ? "Deposit" : "Withdrawal";
        return String.format("%s | %s | ₹%.2f | %s", dateStr, type, Math.abs(amount), memo);
    }
}
package ATMBankingSystem;

import java.util.ArrayList;

/**
 * Represents a bank account associated with a user.
 */
public class Account {

    private String name;
    private String uuid;
    private User holder;
    private ArrayList<Transaction> transactions;

    /**
     * Constructs a new Account and registers it with the user and the bank.
     *
     * @param name   The name/type of the account (e.g., "Savings").
     * @param holder The user who owns this account.
     * @param bank   The bank this account belongs to.
     */
    public Account(String name, User holder, Bank bank) {
        this.name = name;
        this.holder = holder;
        this.transactions = new ArrayList<>();

        // Generate unique account ID from the bank
        this.uuid = bank.getNewAccountUUID();

        // Link this account to user and bank
        holder.addAccount(this);
        bank.addAccount(this);
    }

    /**
     * Returns the UUID of the account.
     *
     * @return Unique account ID.
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Returns the name of the account.
     *
     * @return Account name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a formatted summary line of the account.
     *
     * @return Summary string including account name and balance.
     */
    public String getSummaryLine() {
        return String.format("%s : ₹%.2f", this.name, this.getBalance());
    }

    /**
     * Calculates and returns the current balance of the account.
     *
     * @return Current balance.
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * Adds a transaction to this account.
     *
     * @param amount Amount to deposit (+ve) or withdraw (-ve).
     * @param memo   Description or reason for the transaction.
     */
    public void addTransaction(double amount, String memo) {
        Transaction newTrans = new Transaction(amount, memo);
        this.transactions.add(newTrans);
    }

    /**
     * Prints all transactions in reverse chronological order.
     */
    public void printTransHistory() {
        System.out.println("Transaction history for account: " + this.name);
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i).getSummaryLine());
        }
    }

    /**
     * Returns a list of all transactions.
     *
     * @return List of transactions.
     */
    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(this.transactions); // Return copy to prevent external changes
    }
}

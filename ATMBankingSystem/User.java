package ATMBankingSystem;

import java.util.ArrayList;

/**
 * Represents a banking user with personal info, PIN, UUID, and multiple accounts.
 */
public class User {

    private String firstName;
    private String lastName;
    private String uuid;
    private String pin;
    private ArrayList<Account> accounts;

    /**
     * Creates a new User and registers them with the bank.
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param pin       The user's 4-digit PIN.
     * @param bank      The bank to which this user belongs.
     */
    public User(String firstName, String lastName, String pin, Bank bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.uuid = bank.getNewUserUUID();
        this.accounts = new ArrayList<>();
    }

    /**
     * Adds a new account to the user's list of accounts.
     *
     * @param account The account to add.
     */
    public void addAccount(Account account) {
        if (account != null) {
            this.accounts.add(account);
        }
    }

    /**
     * Returns the user's UUID.
     *
     * @return User ID as a string.
     */

    public String getUUID() {
        return this.uuid;
    }



    /**
     * Validates a given PIN against the user's PIN.
     *
     * @param pin PIN to validate.
     * @return True if correct, false otherwise.
     */
    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    /**
     * Returns the user's first name.
     *
     * @return First name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Prints a summary of all user accounts to the console (for CLI use).
     */
    public void printAccountsSummary() {
        System.out.println("Account Summary for " + this.firstName + ":\n");
        for (int i = 0; i < this.accounts.size(); i++) {
            System.out.println((i + 1) + ") " + this.accounts.get(i).getSummaryLine());
        }
    }

    /**
     * Returns the number of accounts the user holds.
     *
     * @return Number of accounts.
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Gets the account at the specified index.
     *
     * @param index The account index.
     * @return The account object, or null if index is invalid.
     */
    public Account getAccount(int index) {
        if (index >= 0 && index < accounts.size()) {
            return this.accounts.get(index);
        }
        return null;
    }

    /**
     * Returns all accounts of the user.
     *
     * @return List of accounts.
     */
    public ArrayList<Account> getAccounts() {
        return new ArrayList<>(this.accounts); // Return copy to prevent external modification
    }
}
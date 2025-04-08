package ATMBankingSystem;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }


    public User addUser(String firstName, String lastName, String pin) {
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);
        return newUser;
    }



    public void addUserToBank(User user) {
        this.users.add(user);
    }

    public void addAccount(Account acct) {
        this.accounts.add(acct);
    }

    public void addAccount(String accountName, User user) {
        Account newAccount = new Account(accountName, user, this);
      //  user.addAccount(newAccount);
        this.accounts.add(newAccount);
    }


    public User userLogin(String userID, String pin) {
        for (User u : users) {
            if (u.getUUID().equals(userID) && u.validatePin(pin)) {
                return u;
            }
        }
        return null;
    }


    public String getNewUserUUID() {
        String uuid;
        Random rng = new Random();
        int length = 6;
        boolean nonUnique;

        do {
            uuid = "";
            for (int i = 0; i < length; i++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for (User u : users) {
                if (uuid.equals(u.getUUID())) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }

    public String getNewAccountUUID() {
        String uuid;
        Random rng = new Random();
        int length = 10;
        boolean nonUnique;

        do {
            uuid = "";
            for (int i = 0; i < length; i++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            nonUnique = false;
            for (Account a : accounts) {
                if (uuid.equals(a.getUUID())) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }
}
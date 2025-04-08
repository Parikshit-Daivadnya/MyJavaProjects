package ATMBankingSystem;

import java.util.Scanner;

public class ATMmainCLI {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("Global Bank");

        User currentUser;

        // Registration or login loop
        while (true) {
            System.out.println("\nWelcome to " + bank.getName());
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter User ID: ");
                String userID = sc.nextLine();
                System.out.print("Enter PIN: ");
                String pin = sc.nextLine();

                currentUser = bank.userLogin(userID, pin);
                if (currentUser != null) {
                    System.out.println("\nLogin successful. Welcome, " + currentUser.getFirstName() + "!");
                    break;
                } else {
                    System.out.println("Incorrect User ID or PIN. Try again.");
                }

            } else if (choice.equals("2")) {
                System.out.print("Enter First Name: ");
                String firstName = sc.nextLine();
                System.out.print("Enter Last Name: ");
                String lastName = sc.nextLine();
                System.out.print("Set a 4-digit PIN: ");
                String pin = sc.nextLine();

                User newUser = bank.addUser(firstName, lastName, pin);

                System.out.print("Enter name for your first account: ");
                String accName = sc.nextLine();
                bank.addAccount(accName, newUser);

                System.out.println("Registration successful! Your User ID is: " + newUser.getUUID());
                System.out.println("Please login using your User ID and PIN.");
            } else {
                System.out.println("Invalid choice.");
            }
        }

        // Main menu loop
        while (true) {
            System.out.println("\n1) View Accounts");
            System.out.println("2) Add New Account");
            System.out.println("3) Select Account for Transactions");
            System.out.println("4) Logout");
            System.out.print("Enter choice: ");
            String option = sc.nextLine();

            switch (option) {
                case "1":
                    currentUser.printAccountsSummary();
                    break;

                case "2":
                    System.out.print("Enter name for new account: ");
                    String accName = sc.nextLine();
                    bank.addAccount(accName, currentUser);
                    System.out.println("New account created.");
                    break;

                case "3":
                    if (currentUser.numAccounts() == 0) {
                        System.out.println("You have no accounts.");
                        break;
                    }
                    currentUser.printAccountsSummary();
                    System.out.print("Select account number (1-" + currentUser.numAccounts() + "): ");
                    int accIdx;
                    try {
                        accIdx = Integer.parseInt(sc.nextLine()) - 1;
                        Account acc = currentUser.getAccount(accIdx);
                        if (acc == null) {
                            System.out.println("Invalid account number.");
                        } else {
                            handleAccountOperations(sc, acc);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case "4":
                    System.out.println("Thank you for using " + bank.getName() + "! Logging out...");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void handleAccountOperations(Scanner sc, Account acc) {
        while (true) {
            System.out.println("\nAccount: " + acc.getName());
            System.out.println("1) View Transaction History");
            System.out.println("2) Deposit");
            System.out.println("3) Withdraw");
            System.out.println("4) Back to Main Menu");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    acc.printTransHistory();
                    break;

                case "2":
                    try {
                        System.out.print("Enter amount to deposit: ");
                        double depAmt = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter memo: ");
                        String depMemo = sc.nextLine();
                        acc.addTransaction(depAmt, depMemo);
                        System.out.println("Deposit successful.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case "3":
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        double withAmt = Double.parseDouble(sc.nextLine());
                        System.out.print("Enter memo: ");
                        String withMemo = sc.nextLine();
                        acc.addTransaction(-withAmt, withMemo);
                        System.out.println("Withdrawal successful.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
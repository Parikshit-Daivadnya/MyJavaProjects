package ATMBankingSystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ATMmainGUI {

    private Bank bank;
    private User currentUser;
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMmainGUI().showWelcomeScreen());
    }

    public ATMmainGUI() {
        this.bank = new Bank("MyBank");
    }

    private void showWelcomeScreen() {
        frame = new JFrame("ATM Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        JButton registerBtn = new JButton("Register");
        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");

        registerBtn.addActionListener(e -> showRegistrationScreen());
        loginBtn.addActionListener(e -> showLoginScreen());
        exitBtn.addActionListener(e -> System.exit(0));

        frame.getContentPane().removeAll();
        frame.add(new JLabel("Welcome to MyBank ATM System", SwingConstants.CENTER));
        frame.add(registerBtn);
        frame.add(loginBtn);
        frame.add(exitBtn);
        frame.setVisible(true);
    }

    private void showRegistrationScreen() {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JPasswordField pinField = new JPasswordField();

        Object[] fields = {
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "4-digit PIN:", pinField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Register", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String pin = new String(pinField.getPassword());

            if (!pin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(frame, "PIN must be 4 digits.");
                return;
            }

            User user = bank.addUser(firstName, lastName, pin);
            JOptionPane.showMessageDialog(frame, "Registration successful!\nYour User ID is: " + user.getUUID());
        }
    }

    private void showLoginScreen() {
        JTextField userIDField = new JTextField();
        JPasswordField pinField = new JPasswordField();

        Object[] fields = {
                "User ID:", userIDField,
                "PIN:", pinField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String userID = userIDField.getText();
            String pin = new String(pinField.getPassword());

            User user = bank.userLogin(userID, pin);

            if (user == null) {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!");
                return;
            }

            currentUser = user;

            // Ensure the user has at least one account
            if (currentUser.numAccounts() == 0) {
                String accountName = JOptionPane.showInputDialog("No accounts found.\nEnter name for your first account:");
                if (accountName != null && !accountName.trim().isEmpty()) {
                    bank.addAccount(accountName, currentUser);
                }
            }

            showATMMenu();
        }
    }

    private void showATMMenu() {
        String[] options = {"Select Account", "Create New Account", "Logout"};

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    frame,
                    "Choose an operation",
                    "ATM Menu - Welcome " + currentUser.getFirstName(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    selectAccount();
                    break;
                case 1:
                    String accName = JOptionPane.showInputDialog("Enter name for new account:");
                    if (accName != null && !accName.trim().isEmpty()) {
                        bank.addAccount(accName, currentUser);
                        JOptionPane.showMessageDialog(frame, "Account created.");
                    }
                    break;
                case 2:
                    currentUser = null;
                    showWelcomeScreen();
                    return;
                default:
                    return;
            }
        }
    }

    private void selectAccount() {
        if (currentUser.numAccounts() == 0) {
            JOptionPane.showMessageDialog(frame, "No accounts found. Create one first.");
            return;
        }

        String[] accOptions = new String[currentUser.numAccounts()];
        for (int i = 0; i < accOptions.length; i++) {
            accOptions[i] = currentUser.getAccount(i).getSummaryLine();
        }

        int accChoice = JOptionPane.showOptionDialog(
                frame,
                "Select an account:",
                "Your Accounts",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                accOptions,
                accOptions[0]
        );

        if (accChoice >= 0 && accChoice < currentUser.numAccounts()) {
            handleAccountOperations(currentUser.getAccount(accChoice));
        }
    }

    private void handleAccountOperations(Account account) {
        String[] options = {"Summary", "Deposit", "Withdraw", "Transactions", "Back"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    frame,
                    "Account: " + account.getName(),
                    "Operations",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    JOptionPane.showMessageDialog(frame, account.getSummaryLine());
                    break;
                case 1:
                    String depAmt = JOptionPane.showInputDialog("Enter deposit amount:");
                    String depMemo = JOptionPane.showInputDialog("Enter memo:");
                    try {
                        double amt = Double.parseDouble(depAmt);
                        account.addTransaction(amt, depMemo);
                        JOptionPane.showMessageDialog(frame, "Deposit successful.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "Invalid input.");
                    }
                    break;
                case 2:
                    String withAmt = JOptionPane.showInputDialog("Enter withdrawal amount:");
                    String withMemo = JOptionPane.showInputDialog("Enter memo:");
                    try {
                        double amt = Double.parseDouble(withAmt);
                        if (amt > account.getBalance()) {
                            JOptionPane.showMessageDialog(frame, "Insufficient funds.");
                        } else {
                            account.addTransaction(-amt, withMemo);
                            JOptionPane.showMessageDialog(frame, "Withdrawal successful.");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(frame, "Invalid input.");
                    }
                    break;
                case 3:
                    List<Transaction> trans = account.getTransactions();
                    StringBuilder sb = new StringBuilder();
                    for (Transaction t : trans) {
                        sb.append(t.getSummaryLine()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "No transactions.");
                    break;
                case 4:
                default:
                    return;
            }
        }
    }
}

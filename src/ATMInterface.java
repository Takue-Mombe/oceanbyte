import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ATMInterface extends JFrame {
    private Map<String, String> userCredentials;
    private Map<String, Integer> userBalances;
    private String currentUser;
    private JButton loginButton;
    private JButton registerButton;
    private JButton viewBalanceButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton viewTransactionsButton;
    private JButton quitButton;

    public ATMInterface() {
        super("ATM Interface");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        userCredentials = new HashMap<>();
        userBalances = new HashMap<>();

        registerButton = new JButton("Register");
        add(registerButton);

        loginButton = new JButton("Login");
        add(loginButton);

        viewBalanceButton = new JButton("View Balance");
        add(viewBalanceButton);

        withdrawButton = new JButton("Withdraw");
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        add(depositButton);

        transferButton = new JButton("Transfer");
        add(transferButton);

        viewTransactionsButton = new JButton("View Transactions");
        add(viewTransactionsButton);

        quitButton = new JButton("Quit");
        add(quitButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBalance();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer();
            }
        });

        viewTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTransactions();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void registerUser() {
        String userId = JOptionPane.showInputDialog("Enter User ID:");
        String pin = JOptionPane.showInputDialog("Enter PIN:");

        if (userId == null || pin == null || userId.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both User ID and PIN.");
            return;
        }

        if (userCredentials.containsKey(userId)) {
            JOptionPane.showMessageDialog(this, "User already exists. Please choose a different User ID.");
            return;
        }

        userCredentials.put(userId, pin);
        userBalances.put(userId, 0);

        JOptionPane.showMessageDialog(this, "User registered successfully. You can now login.");
    }

    private void loginUser() {
        String userId = JOptionPane.showInputDialog("Enter User ID:");
        String pin = JOptionPane.showInputDialog("Enter PIN:");

        if (userId == null || pin == null || userId.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both User ID and PIN.");
            return;
        }

        if (!userCredentials.containsKey(userId) || !userCredentials.get(userId).equals(pin)) {
            JOptionPane.showMessageDialog(this, "Invalid User ID or PIN. Please try again.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Login successful.");
        currentUser = userId;
    }

    private void viewBalance() {
        JOptionPane.showMessageDialog(this, "Your balance is: " + userBalances.get(currentUser));
    }

    private void withdraw() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to withdraw:");
        if (amountStr == null || amountStr.isEmpty()) {
            return;
        }

        try {
            int amount = Integer.parseInt(amountStr);
            int balance = userBalances.get(currentUser);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                return;
            }

            if (amount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
                return;
            }

            balance -= amount;
            userBalances.put(currentUser, balance);
            JOptionPane.showMessageDialog(this, "Withdrawal successful. Remaining balance: " + balance);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }

    private void deposit() {
        String amountStr = JOptionPane.showInputDialog("Enter amount to deposit:");
        if (amountStr == null || amountStr.isEmpty()) {
            return;
        }

        try {
            int amount = Integer.parseInt(amountStr);
            int balance = userBalances.get(currentUser);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                return;
            }

            balance += amount;
            userBalances.put(currentUser, balance);
            JOptionPane.showMessageDialog(this, "Deposit successful. Current balance: " + balance);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }

    private void transfer() {
        String recipient = JOptionPane.showInputDialog("Enter recipient's User ID:");
        if (recipient == null || recipient.isEmpty()) {
            return;
        }

        if (!userCredentials.containsKey(recipient)) {
            JOptionPane.showMessageDialog(this, "Recipient does not exist.");
            return;
        }

        String amountStr = JOptionPane.showInputDialog("Enter amount to transfer:");
        if (amountStr == null || amountStr.isEmpty()) {
            return;
        }

        try {
            int amount = Integer.parseInt(amountStr);
            int balance = userBalances.get(currentUser);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
                return;
            }

            if (amount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
                return;
            }

            balance -= amount;
            userBalances.put(currentUser, balance);

            int recipientBalance = userBalances.get(recipient);
            recipientBalance += amount;
            userBalances.put(recipient, recipientBalance);

            JOptionPane.showMessageDialog(this, "Transfer successful. Remaining balance: " + balance);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
        }
    }

    private void viewTransactions() {
        // Implement transaction history view
        JOptionPane.showMessageDialog(this, "Viewing transaction history...");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}

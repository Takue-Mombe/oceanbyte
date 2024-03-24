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
    private JLabel messageLabel;
    private JTextField userIdField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton quitButton;

    public ATMInterface() {
        super("ATM Interface");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        userCredentials = new HashMap<>();
        userBalances = new HashMap<>();

        messageLabel = new JLabel("Enter your user ID and PIN:");
        add(messageLabel);

        userIdField = new JTextField(10);
        add(userIdField);

        pinField = new JPasswordField(10);
        add(pinField);

        loginButton = new JButton("Login");
        add(loginButton);

        registerButton = new JButton("Register");
        add(registerButton);

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

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void registerUser() {
        String userId = userIdField.getText();
        String pin = new String(pinField.getPassword());

        if (userId.isEmpty() || pin.isEmpty()) {
            showMessage("Please enter both user ID and PIN.");
            return;
        }

        if (userCredentials.containsKey(userId)) {
            showMessage("User already exists. Please choose a different user ID.");
            return;
        }

        userCredentials.put(userId, pin);
        userBalances.put(userId, 0);

        showMessage("User registered successfully. You can now login.");
    }

    private void loginUser() {
        String userId = userIdField.getText();
        String pin = new String(pinField.getPassword());

        if (userId.isEmpty() || pin.isEmpty()) {
            showMessage("Please enter both user ID and PIN.");
            return;
        }

        if (!userCredentials.containsKey(userId) || !userCredentials.get(userId).equals(pin)) {
            showMessage("Invalid user ID or PIN. Please try again.");
            return;
        }

        showMessage("Login successful.");
        currentUser = userId;
        openTransactionMenu();
    }

    private void openTransactionMenu() {
        // Implement transaction menu here
        // For demonstration, you can display another JFrame or console menu
        System.out.println("Welcome, " + currentUser + "! You can now perform transactions.");
        // Implement transaction functionalities
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private JTextField userIdField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JButton historyButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton quitButton;

    public ATMInterface() {
        super("ATM Interface");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        add(userIdLabel);

        userIdField = new JTextField();
        add(userIdField);

        JLabel pinLabel = new JLabel("PIN:");
        add(pinLabel);

        pinField = new JPasswordField();
        add(pinField);

        loginButton = new JButton("Login");
        add(loginButton);

        historyButton = new JButton("Transaction History");
        add(historyButton);

        withdrawButton = new JButton("Withdraw");
        add(withdrawButton);

        depositButton = new JButton("Deposit");
        add(depositButton);

        transferButton = new JButton("Transfer");
        add(transferButton);

        quitButton = new JButton("Quit");
        add(quitButton);

        // Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement login functionality here
                String userId = userIdField.getText();
                String pin = String.valueOf(pinField.getPassword());
                // Verify user ID and PIN
                // If valid, enable other buttons
                historyButton.setEnabled(true);
                withdrawButton.setEnabled(true);
                depositButton.setEnabled(true);
                transferButton.setEnabled(true);
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement transaction history functionality here
                JOptionPane.showMessageDialog(null, "Transaction History");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement withdraw functionality here
                JOptionPane.showMessageDialog(null, "Withdraw");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement deposit functionality here
                JOptionPane.showMessageDialog(null, "Deposit");
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement transfer functionality here
                JOptionPane.showMessageDialog(null, "Transfer");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int attempts;
    private int maxAttempts = 5; // Change this value to set the maximum number of attempts
    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGame() {
        super("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        instructionLabel = new JLabel("Enter your guess (1-100):");
        add(instructionLabel);

        guessField = new JTextField(10);
        add(guessField);

        guessButton = new JButton("Guess");
        add(guessButton);

        resultLabel = new JLabel("");
        add(resultLabel);

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        add(attemptsLabel);

        randomNumber = generateRandomNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        int guess;
        try {
            guess = Integer.parseInt(guessField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter a number.");
            return;
        }

        attempts--;

        if (guess == randomNumber) {
            resultLabel.setText("Congratulations! You guessed the number.");
            guessButton.setEnabled(false);
        } else if (guess < randomNumber) {
            resultLabel.setText("Too low. Try again.");
        } else {
            resultLabel.setText("Too high. Try again.");
        }

        attemptsLabel.setText("Attempts left: " + attempts);

        if (attempts == 0) {
            resultLabel.setText("Out of attempts. The number was: " + randomNumber);
            guessButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}

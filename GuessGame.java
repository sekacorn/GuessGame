import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessGameUI extends JFrame {
    private String[] words = {"elephant", "analog", "guitar", "computer",
            "jersey", "monkey", "donkey", "mother", "father", "awesome",
            "school", "lion", "bench", "answer", "enter", "house",
            "promote", "given", "sister", "brother"};

    private String currentWord;
    private StringBuffer dashes;
    private int chances;
    private final int MAX_CHANCES = 6;

    private JLabel wordLabel = new JLabel();
    private JLabel statusLabel = new JLabel("Welcome to Hangman!");
    private JTextField guessField = new JTextField(1);
    private JButton guessButton = new JButton("Guess");
    private JButton newGameButton = new JButton("New Game");

    public GuessGameUI() {
        setTitle("Hangman Game");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(wordLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Enter a letter: "));
        centerPanel.add(guessField);
        centerPanel.add(guessButton);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(statusLabel);
        bottomPanel.add(newGameButton);
        add(bottomPanel, BorderLayout.SOUTH);

        guessButton.addActionListener(e -> processGuess());
        newGameButton.addActionListener(e -> startNewGame());

        startNewGame();
    }

    // Starts a new game by selecting a random word
    private void startNewGame() {
        Random rand = new Random();
        currentWord = words[rand.nextInt(words.length)].toLowerCase();
        dashes = new StringBuffer(currentWord.length());
        for (int i = 0; i < currentWord.length(); i++) {
            dashes.append("-");
        }
        chances = MAX_CHANCES;
        updateWordDisplay();
        statusLabel.setText("New game started. You have " + chances + " chances.");
        guessField.setText("");
        guessField.setEditable(true);
        guessButton.setEnabled(true);
    }

    // Handles each guess from the user
    private void processGuess() {
        String input = guessField.getText().toLowerCase();
        if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            JOptionPane.showMessageDialog(this, "Please enter a single valid letter.");
            return;
        }

        char letter = input.charAt(0);
        boolean found = false;

        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == letter && dashes.charAt(i) == '-') {
                dashes.setCharAt(i, letter);
                found = true;
            }
        }

        if (!found) {
            chances--;
        }

        updateWordDisplay();
        guessField.setText("");

        if (dashes.toString().equals(currentWord)) {
            statusLabel.setText("You won! The word was: " + currentWord);
            guessField.setEditable(false);
            guessButton.setEnabled(false);
        } else if (chances <= 0) {
            statusLabel.setText("You lost! The word was: " + currentWord);
            guessField.setEditable(false);
            guessButton.setEnabled(false);
        } else {
            statusLabel.setText("Chances left: " + chances);
        }
    }

    // Updates the word display with dashes and revealed letters
    private void updateWordDisplay() {
        wordLabel.setText("Word: " + dashes.toString());
    }

    // Main method to launch the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GuessGameUI().setVisible(true);
        });
    }
}

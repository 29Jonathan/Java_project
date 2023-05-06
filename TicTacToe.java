import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private String currentPlayer;
    private JLabel statusLabel;

    public TicTacToe() {
        super("s1103525's Tic Tac Toe");
        buttons = new JButton[3][3];
        currentPlayer = "X";

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("MV Boli",Font.BOLD,25));
                buttons[row][col].setFocusable(false);
                buttons[row][col].addActionListener(this);
                gridPanel.add(buttons[row][col]);
            }
        }

        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setFont(new Font("Ink Free", Font.BOLD, 25));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        add(gridPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(currentPlayer);
            if (checkForWin()) {
                statusLabel.setForeground(Color.red);
                statusLabel.setText("Player " + currentPlayer + " wins!");
                disableButtons();
            } else if (checkForDraw()) {
                statusLabel.setForeground(Color.red);
                statusLabel.setText("It's a draw!");
                disableButtons();
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkForWin() {
        String[][] board = new String[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
                return true;
            }
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
                return true;
            }
        }

        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

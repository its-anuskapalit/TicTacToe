import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class TicTacToe extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private boolean player1Turn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        initializeButtons();
    }

    private void initializeButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        if (player1Turn) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        player1Turn = !player1Turn;
        checkForWinner();
    }

    private void checkForWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (checkRow(i) || checkColumn(i)) {
                announceWinner();
                return;
            }
        }
        if (checkDiagonals()) {
            announceWinner();
        }
    }

    private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                !buttons[row][0].getText().equals("");
    }

    private boolean checkColumn(int column) {
        return buttons[0][column].getText().equals(buttons[1][column].getText()) &&
                buttons[1][column].getText().equals(buttons[2][column].getText()) &&
                !buttons[0][column].getText().equals("");
    }

    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) ||
                (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                        buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                        !buttons[0][2].getText().equals(""));
    }

    private void announceWinner() {
        String winner = player1Turn ? "O" : "X";
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}

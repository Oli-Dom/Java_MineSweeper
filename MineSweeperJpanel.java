import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperJpanel extends JPanel {
    private JButton[][] board;
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    private Grid grid;
    ImageIcon bombIcon = new ImageIcon("E:/Java Programs/Java-School-Hw-Repo/Project 2/bomb.jpg");
    ImageIcon bombScaled = new ImageIcon(bombIcon.getImage().getScaledInstance(90, 90, Image.SCALE_FAST));

    public MineSweeperJpanel() {
        setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        board = new JButton[NUM_ROWS][NUM_COLS];
        grid = new Grid(NUM_ROWS, NUM_COLS, 25);
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonClickListener(row, col));
                add(button);
                board[row][col] = button;
            }
        }
        // grid.printBombGrid();
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (grid.isBombAtLocation(row, col)) { // happens if the user clicks a bomb == game over

                for (int i = 0; i < NUM_ROWS; i++) {
                    for (int j = 0; j < NUM_COLS; j++) {
                        int count = grid.getCountAtLocation(i, j);
                        if (grid.isBombAtLocation(i, j)) {
                            board[i][j].setIcon(bombScaled);
                        } else {
                            board[i][j].setText(Integer.toString(count));
                        }
                        board[i][j].setEnabled(false);
                    }
                }
                int option = JOptionPane.showConfirmDialog(null, "Game Over! Play again?", "Game Over",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Reset the game
                    grid = new Grid(NUM_ROWS, NUM_COLS, 25);
                    for (int i = 0; i < NUM_ROWS; i++) {
                        for (int j = 0; j < NUM_COLS; j++) {
                            board[i][j].setText("");
                            board[i][j].setIcon(null);
                            board[i][j].setEnabled(true);
                        }
                    }
                } else {
                    // Exit the application
                    System.exit(0);
                }
            } else { // Happens when the jbutton is not a bomb, it will display count of bombs

                int count = grid.getCountAtLocation(row, col);
                board[row][col].setText(Integer.toString(count));
                board[row][col].setEnabled(false);
                // Check if the game is won
                boolean gameWon = true;
                for (int i = 0; i < NUM_ROWS; i++) {
                    for (int j = 0; j < NUM_COLS; j++) {
                        if (!grid.isBombAtLocation(i, j) && board[i][j].isEnabled()) { // checks for each cell not
                                                                                       // being/being a bomb
                            gameWon = false;
                            break;
                        }
                    }
                }
                if (gameWon) { // if the if-statement above doesnt execute then you win, and oyu get prompted
                               // to play again
                    int option = JOptionPane.showConfirmDialog(null, "Congratulations, you won! Play again?", "You Won",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        // Reset the game
                        grid = new Grid(NUM_ROWS, NUM_COLS, 25);
                        for (int i = 0; i < NUM_ROWS; i++) {
                            for (int j = 0; j < NUM_COLS; j++) {
                                board[i][j].setText("");
                                board[i][j].setIcon(null);
                                board[i][j].setEnabled(true);
                            }
                        }
                    } else {
                        // Exit the application
                        System.exit(0);
                    }
                }
            }
        }
    }

}

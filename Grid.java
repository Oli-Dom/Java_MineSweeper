/*https://youtu.be/QfYbvobZIkY */

import java.util.Random;

public class Grid {
    private boolean[][] bombGrid;
    private int[][] countGrid;
    private int numRows;
    private int numColumns;
    private int numBombs;

    public Grid() {
        this(10, 10, 25);
    }

    public Grid(int rows, int columns) {
        this(rows, columns, 25);
    }

    public Grid(int rows, int columns, int numBombs) {
        this.numRows = rows;
        this.numColumns = columns;
        this.numBombs = numBombs;
        this.bombGrid = new boolean[numRows][numColumns];
        this.countGrid = new int[numRows][numColumns];
        createBombGrid();
        createCountGrid();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public boolean[][] getBombGrid() {

        boolean[][] copy = new boolean[numRows][numColumns];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                copy[row][col] = bombGrid[row][col];
            }
        }
        return copy;
    }

    public int[][] getCountGrid() {

        int[][] copy = new int[numRows][numColumns];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                copy[row][col] = countGrid[row][col];
            }
        }
        return copy;
    }

    public boolean isBombAtLocation(int row, int column) {
        return bombGrid[row][column];
    }

    public int getCountAtLocation(int row, int column) {
        return countGrid[row][column];
    }

    private void createBombGrid() {
        Random random = new Random();
        int bombsPlaced = 0;
        while (bombsPlaced < numBombs) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numColumns);
            if (!bombGrid[row][col]) {
                bombGrid[row][col] = true;
                bombsPlaced++;
            }
        }
    }

    private void createCountGrid() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numColumns; col++) {
                int count = 0;
                if (bombGrid[row][col]) {
                    count++;
                }
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int r = row + i;
                        int c = col + j;
                        if (r >= 0 && r < numRows && c >= 0 && c < numColumns && !(i == 0 && j == 0)) {
                            if (bombGrid[r][c]) {
                                count++;
                            }
                        }
                    }
                }
                countGrid[row][col] = count;
            }
        }
    }

    // public void printBombGrid() {
    // System.out.println("Bomb Grid:");
    // for (int row = 0; row < numRows; row++) {
    // for (int col = 0; col < numColumns; col++) {
    // char c = bombGrid[row][col] ? 'T' : 'F';
    // System.out.print(c + " ");
    // }
    // System.out.println();
    // }
    // }

}

import java.util.*;

// CO5: Inheritance & OOP extensibility
abstract class Game {
    abstract void start();
}

// CO4: Encapsulation & modular design
class Board {
    private char[][] grid; // CO2: 2D Arrays
    private char currentPlayer;

    public Board() {
        grid = new char[3][3];             // CO2
        currentPlayer = 'X';               // CO1: Data Types
        initializeBoard();                 // CO4: modularization
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)         // CO1: loops
            for (int j = 0; j < 3; j++)
                grid[i][j] = '-';
    }

    public void printBoard() {
        for (char[] row : grid) {
            for (char cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    public boolean placeMark(int row, int col) {
        // CO3: Validation using logic, pattern-like checking
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == '-') {
            grid[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        // CO3: Strings & pattern-like logic
        String player = String.valueOf(currentPlayer);

        // rows & columns
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == currentPlayer && grid[i][1] == currentPlayer && grid[i][2] == currentPlayer) ||
                (grid[0][i] == currentPlayer && grid[1][i] == currentPlayer && grid[2][i] == currentPlayer)) {
                return true;
            }
        }

        // diagonals
        if ((grid[0][0] == currentPlayer && grid[1][1] == currentPlayer && grid[2][2] == currentPlayer) ||
            (grid[0][2] == currentPlayer && grid[1][1] == currentPlayer && grid[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    public boolean isFull() {
        for (char[] row : grid)
            for (char cell : row)
                if (cell == '-') return false;
        return true;
    }

    public void switchPlayer() {
        // CO1: Operators + CO4: modular method
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public char getCurrentPlayer() {
        return currentPlayer;  // CO4: getter
    }
}

class TicTacToeGame extends Game {
    private Board board;

    public TicTacToeGame() {
        board = new Board(); // CO4: constructor usage
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Tic Tac Toe ===");
        board.printBoard();

        while (true) {
            try {
                // CO6: Exception Handling for invalid inputs
                System.out.println("Player " + board.getCurrentPlayer() + " turn.");
                System.out.print("Enter row and column (0,1,2): ");

                int r = sc.nextInt();       // CO1: input handling
                int c = sc.nextInt();

                if (!board.placeMark(r, c)) {
                    System.out.println("Invalid move! Try again."); // CO6
                    continue;
                }

                board.printBoard();

                if (board.checkWin()) {      // CO3: algorithmic logic
                    System.out.println("Player " + board.getCurrentPlayer() + " wins!");
                    break;
                }

                if (board.isFull()) {        // CO2: complete array check
                    System.out.println("It's a draw!");
                    break;
                }

                board.switchPlayer();        // CO1: conditional switching
            }
            catch (InputMismatchException e) {   // CO6: robust handling
                System.out.println("Error: Enter numbers only!");
                sc.nextLine(); // clear wrong input
            }
        }

        sc.close();
    }
}

public class Main {
    public static void main(String[] args) {
        // CO5: Polymorphism
        Game game = new TicTacToeGame();
        
        // CO6: Real-world style invocation
        game.start();
    }
}

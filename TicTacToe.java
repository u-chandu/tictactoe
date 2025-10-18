import java.util.Scanner;

public class TicTacToe {
    static char[][] board = new char[3][3];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Game started between Nanna and Chandu\n");
        boolean playAgain;
        do {
            initializeBoard();
            playGame();
            playAgain = askReplay();
        } while (playAgain);
        System.out.println("Thanks for playing!");
    }

    // Initializes the game board
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    // Draws the game board in grid style
    public static void printBoard() {
        System.out.println("Current Board:\n");
        System.out.print("   ");
        for (int col = 0; col < 3; col++) {
            System.out.print(col + "   ");
        }
        System.out.println();
        for (int row = 0; row < 3; row++) {
            System.out.print("  +---+---+---+\n");
            System.out.print(row + " ");
            for (int col = 0; col < 3; col++) {
                System.out.print("| " + board[row][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  +---+---+---+");
    }

    // Runs the game loop
    public static void playGame() {
        char currentPlayerSymbol = 'X';
        String currentPlayerName = "Nanna";
        int moves = 0;

        while (true) {
            printBoard();
            System.out.println(currentPlayerName + "'s turn to play");

            int row, col;
            while (true) {
                System.out.print("Enter row and column (0 1 2): ");
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayerSymbol;
                    moves++;
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            if (checkWin(currentPlayerSymbol)) {
                printBoard();
                System.out.println(currentPlayerName + " wins!");
                break;
            } else if (moves == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // Switch players
            if (currentPlayerSymbol == 'X') {
                currentPlayerSymbol = 'O';
                currentPlayerName = "Chandu";
            } else {
                currentPlayerSymbol = 'X';
                currentPlayerName = "Nanna";
            }
        }
    }

    // Checks if move is valid
    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 &&
               col >= 0 && col < 3 &&
               board[row][col] == ' ';
    }

    // Checks if player has won
    public static boolean checkWin(char player) {
        // Rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player &&
                 board[i][1] == player &&
                 board[i][2] == player) ||
                (board[0][i] == player &&
                 board[1][i] == player &&
                 board[2][i] == player))
                return true;
        }

        // Diagonals
        if ((board[0][0] == player &&
             board[1][1] == player &&
             board[2][2] == player) ||
            (board[0][2] == player &&
             board[1][1] == player &&
             board[2][0] == player))
            return true;

        return false;
    }

    // Asks players if they want to play again
    public static boolean askReplay() {
        System.out.print("Do Nanna and Chandu want to play again? (yes/no): ");
        scanner.nextLine(); // Clear buffer
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
}

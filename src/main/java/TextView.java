import java.util.Arrays;
import java.util.Scanner;

public class TextView {
    private int row;
    private int column;
    private int targetRow;
    private int targetColumn;
    private char actionType;
    public int getRow() { return row; }
    public int getColumn() { return column; }
    public int getTargetRow() { return targetRow; }
    public int getTargetColumn() { return targetColumn; }
    public char getActionType() { return actionType; }
    public static char getUsersNextActionType(Scanner input) {
        boolean validated = false; char command = 'X';
        System.out.print("Enter User Action: ");
        while (!validated) {
            command = input.next().toUpperCase().charAt(0);
            validated = Arrays.asList('A', 'M', 'R', 'S').contains(command);
            if (!validated) System.out.print("Error: Invalid Command. Try again: ");
        }
        return command;
    }

    public static int getValidInt(int min, int max, Scanner input, String message) {
        boolean validated = false; char validInt = 'X'; boolean hasNext = false;
        if (message != null) System.out.print(message + ": ");
        else System.out.print("Enter Input: ");
        while (!validated) {
            hasNext = input.hasNext();
            validInt = input.next().charAt(0);
            if (hasNext) System.out.print(validInt + "\n");
            validated = Character.isDigit(validInt)
                && Character.getNumericValue(validInt) <= max
                && Character.getNumericValue(validInt) >= min;
            if (!validated) System.out.print("Error: " + validInt + " is not valid ("+ min + " - " + max + "). Try again: ");
        }
        return Character.getNumericValue(validInt);
    }

    public void getNextPlayersAction(Game game) {
        Scanner input = new Scanner(System.in);
        this.actionType = getUsersNextActionType(input);
        int numRows = game.getBoard().getNumRows() - 1;
        int numCols = game.getBoard().getNumColumns() - 1;
        row = getValidInt(0, numRows, input, "Enter row for 'fromSquare': 0 - " + numRows);
        column = getValidInt(0, numRows, input, "Enter column for 'fromSquare': 0 - " + numCols);
        targetRow = getValidInt(0, numRows, input, "Enter row for 'toSquare': 0 - " + numRows);
        targetColumn = getValidInt(0, numRows, input, "Enter column for 'toSquare': 0 - " + numCols);
    }

    public void updateView(Game game) {
        System.out.println(game);
    }

    public void printEndOfGameMessage(Game game) {
        System.out.println("Game over. Player #" + game.getWinner().getPlayerNumber() + " has won!");
    }
}

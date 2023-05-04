import java.util.Arrays;
import java.util.Scanner;

public class TextView {
    private int row;
    private int column;
    private int targetRow;
    private int targetColumn;
    private char actionType;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getTargetRow() {
        return targetRow;
    }

    public int getTargetColumn() {
        return targetColumn;
    }

    public char getActionType() {
        return actionType;
    }

    public static char getUsersNextActionType(Scanner input) {
		boolean validated = false; char command = 'X';
		System.out.print("Enter User Action: ");
		do {
			command = input.next().toUpperCase().charAt(0);
			validated = Arrays.asList('A', 'M', 'R', 'S').contains(command);
			if (!validated) System.out.print("Error: Invalid Command. Try again: ");
		} while(!validated);
		System.out.println(command);
		return command;
	}

	public static int getValidInt(int min, int max, Scanner input) {
		boolean validated = false; char validInt = 'X';
		System.out.print("Enter Input: ");
		do {
			validInt = input.next().charAt(0);
			validated = Character.isDigit(validInt)
                && Character.getNumericValue(validInt) <= max
                && Character.getNumericValue(validInt) >= min;
			if (!validated) System.out.print("Error: Invalid Int. Try again: ");
		} while(!validated);
		System.out.println(validInt);
		return Character.getNumericValue(validInt);
	}


    public void getNextPlayersAction(Game game) {
        Scanner input = new Scanner(System.in);
        this.actionType = getUsersNextActionType(input);
        int numRows = game.getBoard().getNumRows();
        int numCols = game.getBoard().getNumColumns();
        System.out.println("Enter row for 'fromSquare': 0 - " + (numRows - 1));
        row = getValidInt(0, numRows, input);
        System.out.println("Enter column for 'fromSquare': 0 - " + (numCols - 1));
        column = getValidInt(0, numRows, input);
        System.out.println("Enter row for 'toSquare': 0 - " + (numRows - 1));
        targetRow = getValidInt(0, numRows, input);
        System.out.println("Enter column for 'toSquare': 0 - " + (numCols - 1));
        targetColumn = getValidInt(0, numRows, input);
    }

    public void updateView(Game game) {
        System.out.println(game);
    }

    public void printEndOfGameMessage(Game game) {
        System.out.println("Game over. Player #" + game.getWinner().getPlayerNumber() + " has won!");
    }
}

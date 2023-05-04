import java.util.ArrayList;
public class Controller {
    private Game game;
    private TextView textView;
    public Game setUpGameModel() {
        ArrayList<Unit> piecesTeamA = new ArrayList<Unit>();
        ArrayList<Unit> piecesTeamB = new ArrayList<Unit>();
        Team teamA = new Team("Blu", piecesTeamA);
        Team teamB = new Team("Red", piecesTeamB);
        Player playerTwo = new Player(2, false, teamB);
        Player playerOne = new Player(1, true, teamA);

        BartSimpsonUnit bs = new BartSimpsonUnit();
        TomJerryUnit tj = new TomJerryUnit();
        bs.setTeamColor("Blu");
        tj.setTeamColor("Blu");
        piecesTeamA.add(bs);
        piecesTeamA.add(tj);

        BartSimpsonUnit bs2 = new BartSimpsonUnit();
        TomJerryUnit tj2 = new TomJerryUnit();
        bs2.setTeamColor("Red");
        tj2.setTeamColor("Red");
        piecesTeamB.add(bs2);
        piecesTeamB.add(tj2);

        Game game = new Game(8, 8, playerOne, playerTwo);
        for (Unit u : piecesTeamA) game.getBoard().findRandomEmptySpace().setUnit(u);
        for (Unit u : piecesTeamB) game.getBoard().findRandomEmptySpace().setUnit(u);
        return game;
    }

    public Controller() {
        game = setUpGameModel(); // Call setUpGameModel and assign its return value to the Game property
        textView = new TextView(); // Create an instance of the TextView class and assign it to the textView property
        textView.updateView(game); // Call updateView to display the game to our players
    }

    public void carryOutAction(int fromRow, int fromCol, int toRow, int toCol, char actionType) {
        if (!Rules.checkValidAction(game, fromRow, fromCol, toRow, toCol, actionType)) {
            if (actionType == 'M') System.out.println("Invalid move.");
            else if (actionType == 'A') System.out.println("Invalid attack.");
            else if (actionType == 'R') System.out.println("Invalid recruit.");
            else if (actionType == 'S') System.out.println("Invalid spawn.");
        } else {
            Action action = null;
            if (actionType == 'M') action = new ActionMove(game, fromRow, fromCol, toRow, toCol);
            else if (actionType == 'A') action = new ActionAttack(game, fromRow, fromCol, toRow, toCol);
            else if (actionType == 'R') action = new ActionRecruit(game, fromRow, fromCol, toRow, toCol);
            else if (actionType == 'S') action = new ActionSpawn(game, fromRow, fromCol, toRow, toCol);
            else { System.out.println("Invalid action type."); }
            if (action instanceof Action) action.perfomAction();
        }
    }

    public void playGame() {
        while (!game.isGameEnded()) {
            textView.getNextPlayersAction(game);
            // Check if the action is valid
            while (!Rules.checkValidAction(game, textView.getRow(), textView.getColumn(),
                textView.getTargetRow(), textView.getTargetColumn(), textView.getActionType())) {
                System.out.println("Invalid action. Please try again.");
                textView.getNextPlayersAction(game);
            }

            carryOutAction(textView.getRow(), textView.getColumn(),
                textView.getTargetRow(), textView.getTargetColumn(), textView.getActionType()); // Carry out the action
            textView.updateView(game); // Print the game board

            if (game.isGameEnded()) {
                Player winner = game.getWinner();
                if (winner != null) System.out.println("Game over! Player " + winner.getPlayerNumber() + " has won!");
                else System.out.println("Game over! It's a tie!");
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.playGame();
    }
}

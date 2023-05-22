import java.util.ArrayList;
import java.util.Collections;
public class Controller {
    private Game game;
    private GameEventsLinkedList gameEvents;
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
        this.game = setUpGameModel(); // Call setUpGameModel and assign its return value to the Game property
        this.textView = new TextView(); // Create an instance of the TextView class and assign it to the textView property
        this.textView.updateView(game); // Call updateView to display the game to our players
        this.gameEvents = new GameEventsLinkedList();
    }

    public void carryOutAction(int fromRow, int fromCol, int toRow, int toCol, char actionType) {
        Action action = null;
        if (actionType == 'M') action = new ActionMove(game, fromRow, fromCol, toRow, toCol);
        else if (actionType == 'A') action = new ActionAttack(game, fromRow, fromCol, toRow, toCol);
        else if (actionType == 'R') action = new ActionRecruit(game, fromRow, fromCol, toRow, toCol);
        else if (actionType == 'S') action = new ActionSpawn(game, fromRow, fromCol, toRow, toCol);
        else System.out.println("Invalid action type.");
        if (action instanceof Action) {
            action.perfomAction();
            int playerNumber = this.game.getCurrentPlayer().getPlayerNumber();
            this.gameEvents.push(new GameEventNode(new GameEvent(playerNumber, action.toString().split(":")[0], action.toString())));
        }
    }

    public void playGame() {
        while (!this.game.isGameEnded()) {
            textView.getNextPlayersAction(this.game);
            while (!Rules.checkValidAction(this.game, textView.getRow(), textView.getColumn(),
                textView.getTargetRow(), textView.getTargetColumn(), textView.getActionType())) {
                char actionType = textView.getActionType();
                if (actionType == 'M') System.out.println("Invalid move.");
                else if (actionType == 'A') System.out.println("Invalid attack.");
                else if (actionType == 'R') System.out.println("Invalid recruit.");
                else if (actionType == 'S') System.out.println("Invalid spawn.");
                textView.getNextPlayersAction(this.game);
            }

            carryOutAction(textView.getRow(), textView.getColumn(), textView.getTargetRow(), textView.getTargetColumn(), textView.getActionType());
            textView.updateView(this.game); // Print the game board

            if (this.game.isGameEnded()) {
                ArrayList<GameEventsLinkedList> events = new ArrayList<>();
                String[] eventStrings = {"Move", "Attack", "Spawn", "Recruit", "Stun"};
                for (int i = 0; i < 4; i++) {
                    GameEventsLinkedList addition = this.gameEvents;
                    for (int z = 0; z < 4; z++)
                        if (z != i) addition = addition.extract(eventStrings[z]);
                    events.add(events.size(), addition);
                }
                Collections.sort(events, (a, b) -> a.size - b.size);
                System.out.println("Total Moves: ");
                for (GameEventsLinkedList e : events)
                    if (e.size > 0) System.out.println(e.head.getGameState().getEventType() + ": " + e.size);
                System.out.println();

                GameEventNode lastMove = this.gameEvents.pop();
                if (lastMove != null)
                    System.out.println("Winning Move: " + lastMove.getGameState().getEventDetails());
                this.textView.printEndOfGameMessage(this.game);
            }
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.playGame();
    }
}

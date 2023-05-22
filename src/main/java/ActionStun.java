// New Action Modification
/**
 * This class is for a stun attack.
 * The Burger King can stun an enemy with his whopper and prevent them from moving.
 * Or, unstun his teammate. Recruit the Burger King in order to unstun your teammate
 */
public class ActionStun extends Action {
    public ActionStun(Game game, int row, int column, int targetRow, int targetColumn){
        super(game, row, column, targetRow, targetColumn);
    }
    public void perfomAction(){
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare square = squares[super.row][super.column];
        BoardSquare target = squares[super.targetRow][super.targetColumn];
        Unit attacker = square.getUnit();
        Unit victim = target.getUnit();
        Boolean isOpponent = victim != null && victim.getTeamColor() != victim.getTeamColor();
        if (!(attacker instanceof BurgerKingUnit)) return;
        if (isOpponent) victim.setMovementModifier(-1); // cannot move
        else victim.setMovementModifier(0); // unstun teammate
    }
}

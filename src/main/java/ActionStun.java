// New Action Modification
public class ActionStun extends Action{
    public ActionStun(Game game, int row, int column, int targetRow, int targetColumn){
        super(game, row, column, targetRow, targetColumn);
    }
    public void perfomAction(){
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare square = squares[super.row][super.column];
        BoardSquare target = squares[super.targetRow][super.targetColumn];
        Unit attacker = square.getUnit();
        Unit victim = target.getUnit();
        if (!(attacker instanceof BurgerKingUnit)) return;
        victim.setMovement(0); // cannot move
    }
}

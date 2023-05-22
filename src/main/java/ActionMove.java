class ActionMove extends Action {
    public ActionMove(Game game, int row, int column, int targetRow, int targetColumn)
        {super(game, row, column, targetRow, targetColumn);}
    public void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare square = squares[super.row][super.column];
        BoardSquare target = squares[super.targetRow][super.targetColumn];
        target.setUnit(square.getUnit()); // ● set Unit onto ‘to square’
        square.removeUnit(); // ● remove Unit on ‘from square’
        super.game.changeTurn(); // ● change the turn
    }
    @Override
    public String toString() {
        return "Move: " + game.getCurrentPlayer().getTeam().getTeamColor() + " team's "
            + game.getBoardSquares()[super.row][super.column].getUnit().getName() + " unit moves from "
            + super.row + ", " + super.column + " to " + super.targetRow + ", " + super.targetColumn;
    }
}
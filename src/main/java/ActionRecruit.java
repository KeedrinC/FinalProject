class ActionRecruit extends Action {
    public ActionRecruit(Game game, int row, int column, int targetRow, int targetColumn)
        {super(game, row, column, targetRow, targetColumn);}
    public void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare target = squares[super.targetRow][super.targetColumn];
        game.getOpponentPlayer().getTeam().removeUnitsFromTeam(target.getUnit()); // ● remove the Unit that was recruited from the opponent’s team
        game.getCurrentPlayer().getTeam().addUnitsToTeam(target.getUnit()); // ● add the Unit that was recruited to the current team
        super.game.changeTurn(); // ● change the turn
    }
}
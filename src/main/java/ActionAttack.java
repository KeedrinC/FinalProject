class ActionAttack extends Action {
    public ActionAttack(Game game, int row, int column, int targetRow, int targetColumn)
        {super(game, row, column, targetRow, targetColumn);}
    public void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare square = squares[super.row][super.column];
        BoardSquare target = squares[super.targetRow][super.targetColumn];
        Unit attacker = square.getUnit();
        Unit victim = target.getUnit();
        if (!(attacker instanceof TomJerryUnit)) return; // ● Attack if the Unit is of type TomJerryUnit (BartSimpsonUnits can only recruit)
        if (victim instanceof TomJerryUnit) { // for attacking opponent units it should:
            ((TomJerryUnit) victim).takeDamage(((TomJerryUnit) attacker).dealDamage()); // ● Utilize TomJerryUnit’s dealDamage and takeDamage() methods and
            if (((TomJerryUnit) victim).getHealth() <= 0) target.removeUnit(); // ● remove an attacked TomJerryUnit if their health is 0 or below.
        } else if (victim instanceof BartSimpsonUnit) target.removeUnit(); // ● Remove a BartSimpsonUnit if attacked (i.e. health fields are not used - do not use TomJerryUnit’s dealDamage)
        if (target.isEmpty()) { // ● Move the Unit on the From Square to the To Square (only if the opponents unit is “dead”)
            square.removeUnit();
            target.setUnit(attacker);
            ((game.getCurrentPlayer().getTeam().getTeamColor() == victim.getTeamColor())
                ? game.getCurrentPlayer() : game.getOpponentPlayer())
                .getTeam().removeUnitsFromTeam(victim); // Hint: two “removes” must occur - one from a square and one from a team - when a unit “dies”.
        }
        super.game.changeTurn(); // ● Change the turn
    }
}
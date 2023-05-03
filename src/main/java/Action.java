abstract class Action {
    protected Game game;
    protected int rowIndex, columnIndex;
    protected int toRowIndex, toColumnIndex;
    abstract void perfomAction();
    Action(Game game, int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex) {
        this.game = game;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.toRowIndex = toRowIndex;
        this.toColumnIndex = toColumnIndex;
    }
}

class ActionMove extends Action {
    ActionMove(Game game, int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex)
        {super(game, rowIndex, columnIndex, toRowIndex, toColumnIndex);}
    void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare current = squares[super.rowIndex][super.columnIndex];
        BoardSquare target = squares[super.toRowIndex][super.toColumnIndex];
        target.setUnit(current.getUnit()); // ● set Unit onto ‘to square’
        current.removeUnit(); // ● remove Unit on ‘from square’
        super.game.changeTurn(); // ● change the turn
    }
}

class ActionSpawn extends Action {
    ActionSpawn(Game game, int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex)
        {super(game, rowIndex, columnIndex, toRowIndex, toColumnIndex);}
    void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare current = squares[super.rowIndex][super.columnIndex];
        BoardSquare target = squares[super.toRowIndex][super.toColumnIndex];
        Unit spawn = current.getUnit().spawn(); // ● call the Unit on the ‘from square’s’ spawn method
        super.game.getCurrentPlayer().getTeam().addUnitsToTeam(spawn); // ● add the Unit that was just created to the current team
        target.setUnit(spawn); // ● put Unit that was just created Unit on the board in the ‘to square’
        super.game.changeTurn(); // ● change the turn
    }
}

class ActionRecruit extends Action {
    ActionRecruit(Game game, int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex)
        {super(game, rowIndex, columnIndex, toRowIndex, toColumnIndex);}
    void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare target = squares[super.toRowIndex][super.toColumnIndex];
        game.getOpponentPlayer().getTeam().members.remove(target.getUnit()); // ● remove the Unit that was recruited from the opponent’s team
        game.getCurrentPlayer().getTeam().members.add(target.getUnit()); // ● add the Unit that was recruited to the current team
        super.game.changeTurn(); // ● change the turn
    }
}

class ActionAttack extends Action {
    ActionAttack(Game game, int rowIndex, int columnIndex, int toRowIndex, int toColumnIndex)
        {super(game, rowIndex, columnIndex, toRowIndex, toColumnIndex);}
    void perfomAction() {
        BoardSquare[][] squares = super.game.getBoardSquares();
        BoardSquare current = squares[super.rowIndex][super.columnIndex];
        BoardSquare target = squares[super.toRowIndex][super.toColumnIndex];
        Unit attacker = current.getUnit();
        Unit opponent = target.getUnit();
        if (!(attacker instanceof TomJerryUnit)) return; // ● Attack if the Unit is of type TomJerryUnit (BartSimpsonUnits can only recruit)
        if (opponent instanceof TomJerryUnit) { // for attacking opponent units it should:
            ((TomJerryUnit) opponent).takeDamage(((TomJerryUnit) attacker).dealDamage()); // ● Utilize TomJerryUnit’s dealDamage and takeDamage() methods and
            if (((TomJerryUnit) opponent).getHealth() <= 0) target.removeUnit(); // ● remove an attacked TomJerryUnit if their health is 0 or below.
        } else if (opponent instanceof BartSimpsonUnit) target.removeUnit(); // ● Remove a BartSimpsonUnit if attacked (i.e. health fields are not used - do not use TomJerryUnit’s dealDamage)
        if (target.isEmpty) { // ● Move the Unit on the From Square to the To Square (only if the opponents unit is “dead”)
            current.removeUnit();
            target.setUnit(attacker);
            game.getOpponentPlayer().getTeam().members.remove(opponent); // Hint: two “removes” must occur - one from a square and one from a team - when a unit “dies”.
        }
        super.game.changeTurn(); // ● Change the turn
    }
}
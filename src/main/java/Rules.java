public class Rules {
    public static boolean checkValidAction(Game game, int fromRow, int fromColumn, int toRow, int toColumn, char actionType) {
        BoardSquare[][] squares = game.getBoardSquares();
        BoardSquare current = squares[fromRow][fromColumn];
        BoardSquare target = squares[toRow][toColumn];
        Unit currentUnit = current.getUnit();
        Unit targetUnit = target.getUnit();
        Boolean isOpponent = targetUnit != null && targetUnit.getTeamColor() != currentUnit.getTeamColor();
        if (currentUnit == null || currentUnit.getTeamColor() != game.getCurrentPlayer().getTeam().getTeamColor()) return false;
        if (!game.getBoard().inBounds(fromRow, fromColumn) || !game.getBoard().inBounds(toRow, toColumn)) return false;
        // New Rule Modification: stunned units cannot move
        if (actionType == 'M' && currentUnit.getMovementModifier() < 0) return false;
        return (actionType == 'M' && target.isEmpty() && currentUnit.validMovePath(fromRow, fromColumn, toRow, toColumn))
            || (actionType == 'S' && target.isEmpty() && currentUnit.validSpawnPath(fromRow, fromColumn, toRow, toColumn))
            || (actionType == 'R' && currentUnit instanceof Recruiter && isOpponent
                && ((Recruiter) currentUnit).validRecruitPath(toRow, fromColumn, toRow, toColumn))
            || (actionType == 'A' && currentUnit instanceof Attacker && isOpponent
                && ((Attacker) currentUnit).validAttackPath(toRow, fromColumn, toRow, toColumn))
            || (actionType == 'W' && currentUnit instanceof BurgerKingUnit && isOpponent
                && ((BurgerKingUnit) currentUnit).validStunPath(toRow, fromColumn, toRow, toColumn));
    }
}

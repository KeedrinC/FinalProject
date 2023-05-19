public class BurgerKingUnit extends Attacker {
    private boolean whopper;

    public BurgerKingUnit(char symbol, String name, double health, double healthModifier,
                          double damage, double damageModifier, int luck, int xCor, int yCor, int movement,
                          int movementModifier, boolean whopper) {
        super(symbol, name, health, healthModifier, damage, damageModifier, luck,
                xCor, yCor, movement, movementModifier);
        this.whopper = whopper;
    }
    public BurgerKingUnit() {
        this('K', "Burger King", 100.0, 0.0, 25.0, 0.0, 0,
                5, 5, 1, 0, true);
    }
    public boolean getWhopper() { return whopper; }
    public void setWhopper(boolean whopper) { this.whopper = whopper; }
    public boolean canSpawn() { return true; }

    public BurgerKingUnit spawn() {
        BurgerKingUnit newBurgerKing = new BurgerKingUnit(
                Character.toLowerCase(this.symbol), "Burger King", 100.0, 5.0, 25.0, 10.0, 0, 1,
                1, 1, 1, true);
        return newBurgerKing;
    }

    public double dealDamage() {
        //deals less damage than Tom and Jerry but can stun
        double totalDamage = this.damage + damageModifier;
        if (this.getWhopper())
            totalDamage += 5;
        return totalDamage;
    }

    public boolean validStunPath(int row, int column, int targetRow, int targetColumn) {
        // can only stun units that are 3 spaces away in any direction from a Burger King
        if (targetRow == row + 3 || targetColumn == column + 3
           || targetRow == row - 3 || targetColumn == column - 3) {
            return true;
        }
        return false;
    }

    @Override
	public boolean validAttackPath(int row, int column, int targetRow, int targetColumn) {
		if (targetRow >= 0 || targetRow <= 0 || targetColumn == 2 || targetColumn == 1) {
			return true;
		}
		return false;
	}

    @Override
	public boolean validMovePath(int row, int column, int targetRow, int targetColumn) {
		if (targetRow == 1 || targetRow == 2 || targetColumn >= 0 || targetColumn <= 0) { //Any spaces up and down the column, only two spaces on the row
			return true;
		}
		return false;
	}

    @Override
	public boolean validSpawnPath(int row, int column, int targetRow, int targetColumn) {
		return false;
	}
}

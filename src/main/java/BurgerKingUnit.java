// New Unit Modifications
/**
 * This class is for our Burger King game piece. The Burger King is a tank, and has lots of health.
 * He deals less damage than Tom and Jerry but can stun opponents or unstun teammates.
 */
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
        this('K', "Burger King", 300.0, 0.0, 15.0, 0.0, 0,
                5, 5, 1, 0, true);
    }
    public boolean getWhopper() { return whopper; }
    public void setWhopper(boolean whopper) { this.whopper = whopper; }
    public boolean canSpawn() { return false; } // there can only be one king

    public BurgerKingUnit spawn() {
        BurgerKingUnit newBurgerKing = new BurgerKingUnit(
                Character.toLowerCase(this.symbol), "Burger King", 100.0, 5.0, 25.0, 10.0, 0, 1,
                1, 1, 1, true);
        return newBurgerKing;
    }

    public double dealDamage() {
        double totalDamage = this.damage + damageModifier;
        if (this.getWhopper())
            totalDamage += 5;
        return totalDamage;
    }

    public boolean validStunPath(int row, int column, int targetRow, int targetColumn) {
        // can stun units that are 1 space away in any direction
        return this.validMovePath(row, column, targetRow, targetColumn);
    }

    @Override
	public boolean validAttackPath(int row, int column, int targetRow, int targetColumn) {
        // can attack units that are 1 space away in any direction
        return this.validMovePath(row, column, targetRow, targetColumn);
	}

    @Override
	public boolean validMovePath(int row, int column, int targetRow, int targetColumn) {
		// can only move one space away in any direction
        return Math.abs(targetRow - row) <= 1 && Math.abs(targetColumn - column) <= 1;
	}

    @Override
	public boolean validSpawnPath(int row, int column, int targetRow, int targetColumn) {
        // can only spawn one space away in any direction
        return this.validMovePath(row, column, targetRow, targetColumn);
	}
}

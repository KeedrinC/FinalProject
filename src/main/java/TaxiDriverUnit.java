// New Unit Modifications
/**
 * This class is for our Taxi Driver game piece. The Taxi Driver moves faster than other
 * pieces, including Bart, and has the ability to recruit enemies when near.
 */

public class TaxiDriverUnit extends BartSimpsonUnit {
    private final int MAX_NUM_SPAWNED = 2;
    public TaxiDriverUnit(char symbol, String name, double health, double healthModifier, double damage,
        double damageModifier, int luck, int xCor, int yCor, int movement,
        int movementModifier, int numTimesSpawned, boolean distract, boolean recruit) {
        super(symbol, name, health, healthModifier, damage, damageModifier, luck,
                xCor, yCor, movement, movementModifier, numTimesSpawned, distract, recruit);
    }

    public TaxiDriverUnit() {
        this('D', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
                5, 5, 3, 0, 0,true, true);
    }

    public boolean canSpawn() {
        return Character.isUpperCase(this.getSymbol())
                && super.getNumTimesSpawned() < this.MAX_NUM_SPAWNED;
    }

    public TaxiDriverUnit spawn() {
        TaxiDriverUnit newDriver = new TaxiDriverUnit(
            Character.toLowerCase(this.symbol), "Taxi Driver", 100.0, 5.0, 25.0, 10.0, 0, 1,
            1, 1, 1, 0, true, true);
        super.setNumTimesSpawned(super.getNumTimesSpawned() + 1);
        return newDriver;
    }

    @Override
    public boolean validMovePath(int row, int column, int targetRow, int targetColumn) {
        // can move 3 spaces away in any direction
        int targetDistance = Math.abs(targetRow - row);
        int columnDistance = Math.abs(targetColumn - column);
        if (targetDistance > 3 || columnDistance > 3) return false;
        return (targetDistance == columnDistance || !(targetDistance != 0 && columnDistance != 0));
    }

    @Override
    public boolean validRecruitPath(int row, int column, int targetRow, int targetColumn) {
        // can recruit units that are 2 spaces away in any direction from a Taxi Driver
        return Math.abs(targetRow - row) <= 2 && Math.abs(targetColumn - column) <= 2;
    }
    @Override
	public boolean validSpawnPath(int row, int column, int targetRow, int targetColumn) {
        // can spawn units that are 1 space away in the same row
        if (row != targetRow) return false;
        return Math.abs(targetRow - row) == 1;
	}
}

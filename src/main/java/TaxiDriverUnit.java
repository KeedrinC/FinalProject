public class TaxiDriverUnit extends BartSimpsonUnit{
    final int MAX_NUM_SPAWNED = 2;
    private int numTimesSpawned;
    private boolean recruit;

    public TaxiDriverUnit(char symbol, String name, double health, double healthModifier, double damage,
                          double damageModifier, int luck, int xCor, int yCor, int movement,
                          int movementModifier, int numTimesSpawned, boolean distract, boolean recruit) {
        super(symbol, name, health, healthModifier, damage, damageModifier, luck,
                xCor, yCor, movement, movementModifier, numTimesSpawned, distract, recruit);

    }
    public TaxiDriverUnit() {
        this('D', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
                5, 5, 1, 0, 0,true, true);
    }
    public int getNumTimesSpawned() {
        return this.numTimesSpawned;
    }
    public void setNumTimesSpawned(int numTimesSpawned) {
        this.numTimesSpawned = numTimesSpawned;
    }
    public boolean canRecruit() {
        return recruit;
    }
    public void setRecruit(boolean recruit) {
        this.recruit = recruit;
    }
    public boolean canSpawn() {
        return Character.isUpperCase(this.getSymbol())
                && this.numTimesSpawned < this.MAX_NUM_SPAWNED;
    }
    public TaxiDriverUnit spawn() {
        TaxiDriverUnit newDriver = new TaxiDriverUnit(
                Character.toLowerCase(this.symbol), "Taxi Driver", 100.0, 5.0, 25.0, 10.0, 0, 1,
                1, 1, 1, 0, true, true);
        this.numTimesSpawned++;
        return newDriver;
    }
    public boolean validRecruitPath(int row, int column, int targetRow, int targetColumn) {
        // can only recruit units that are 1 space away in any direction from a Taxi Driver
        if (targetRow == row + 1 || targetColumn == column + 1
            || targetRow == row - 1 || targetColumn == column - 1) {
            return true;
        }
        return false;
    }
}

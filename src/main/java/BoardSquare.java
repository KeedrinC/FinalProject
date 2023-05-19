
/**
 * This class represents every space on our board along with it's color.
 * We keep track of if a space is empty based on whether it contains a unit.
 */

class BoardSquare {
    private boolean isEmpty = true;
    private Unit unit;
    private String color;
    public BoardSquare(String color) {
        this.color = color;
    }

    public Unit getUnit() { return this.unit; }
    public boolean isEmpty() { return this.isEmpty; }
    public String getSquareColor() { return this.color; }
    public void setUnit(Unit replacement) {
        this.unit = replacement;
        this.isEmpty = false;
    }
    public Unit removeUnit() {
        Unit removed = this.unit;
        this.unit = null;
        this.isEmpty = true;
        return removed;
    }
    public String toString() {
        if (this.unit == null) return "-------";
        return "-" + unit.toString() + "-";
    }
}
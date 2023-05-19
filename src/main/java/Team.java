import java.util.ArrayList;

/**
 * Each player can have many pieces.
 * This class represents all of the pieces controlled by a player, or pieces on the same team.
 */

public class Team {
    private String color;
    private ArrayList<Unit> members;
    public Team(String color, ArrayList<Unit> members) {
        this.color = color;
        if (members != null)
            for (Unit m : members) m.setTeamColor(this.color);
        this.members = members;
    }
    public String getTeamColor() {
        return this.color;
    }
    public ArrayList<Unit> getTeamUnits() {
        return this.members;
    }
    public void removeUnitsFromTeam(Unit subtracted) {
        subtracted.setTeamColor(null);
        this.members.remove(subtracted);
    }
    public void addUnitsToTeam(Unit addition) {
        addition.setTeamColor(this.color);
        this.members.add(addition);
    }

    @Override
    public String toString() {
        String str = this.getTeamColor() + "\n";
        if (members != null)
            for (Unit m : members)
                str += m.toString() + "\n";
        return str;
    }
}

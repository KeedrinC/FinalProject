import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionTest {
    protected Game game;
    ActionTest() {
        Player one = new Player(1, new Team("red", new ArrayList<Unit>(Arrays.asList(new BartSimpsonUnit(), new TomJerryUnit()))));
        Player two = new Player(2, new Team("blue", new ArrayList<Unit>(Arrays.asList(new TomJerryUnit()))));
        game = new Game(2, 2, one, two); // 2x2 test environment
        game.getBoardSquares()[0][0].setUnit(one.getTeam().getTeamUnits().get(0));
        game.getBoardSquares()[0][1].setUnit(one.getTeam().getTeamUnits().get(1));
        game.getBoardSquares()[1][1].setUnit(two.getTeam().getTeamUnits().get(0));
    }
    @Test
    void actionMove() {
        int[] pos = {0, 0}, target = {1, 0}; // move down
        ActionMove m = new ActionMove(game, pos[0], pos[1], target[0], target[1]);
        System.out.println(m.toString());

        assert(!game.getBoardSquares()[pos[0]][pos[1]].isEmpty());      // current position isn't empty
        assert(game.getBoardSquares()[target[0]][target[1]].isEmpty()); // target position is empty
        m.perfomAction();
        assert(game.getBoardSquares()[pos[0]][pos[1]].isEmpty());       // current position is empty
        assert(!game.getBoardSquares()[target[0]][target[1]].isEmpty());// target position isn't empty
    }
    @Test
    void actionAttackBS() { // TomJerryUnit attacks BartSimpsonUnit
        int[] pos = {1, 1}, target = {0, 0}; // attack diagonally
        ActionAttack m = new ActionAttack(game, pos[0], pos[1], target[0], target[1]);
        System.out.println(m.toString());
        Unit attacker = game.getBoardSquares()[pos[0]][pos[1]].getUnit();
        Unit victim = game.getBoardSquares()[target[0]][target[1]].getUnit();

        assert(!game.getBoardSquares()[pos[0]][pos[1]].isEmpty());      // current position isn't empty
        assert(!game.getBoardSquares()[target[0]][target[1]].isEmpty()); // target position isn't empty
        m.perfomAction();
        assert(game.getBoardSquares()[pos[0]][pos[1]].isEmpty());       // current position is empty
        assert(!game.getBoardSquares()[target[0]][target[1]].isEmpty());// target position isn't empty
        assertEquals(game.getBoardSquares()[target[0]][target[1]].getUnit(), attacker); // attacker moved to target
        assertFalse(game.getCurrentPlayer().getTeam().getTeamUnits().contains(victim));
        assertFalse(game.getOpponentPlayer().getTeam().getTeamUnits().contains(victim));
    }
    @Test
    void actionAttackTJ() { // TomJerryUnit attacks TomJerryUnit
        int[] pos = {1, 1}, target = {0, 1}; // attack upwards
        ActionAttack m = new ActionAttack(game, pos[0], pos[1], target[0], target[1]);
        Unit attacker = game.getBoardSquares()[pos[0]][pos[1]].getUnit();
        Unit victim = game.getBoardSquares()[target[0]][target[1]].getUnit();
        double victimHealth = victim.getHealth();

        assert(!game.getBoardSquares()[pos[0]][pos[1]].isEmpty());      // current position isn't empty
        assert(!game.getBoardSquares()[target[0]][target[1]].isEmpty()); // target position isn't empty
        m.perfomAction();
        assert(!game.getBoardSquares()[pos[0]][pos[1]].isEmpty());       // current position is empty
        assert(!game.getBoardSquares()[target[0]][target[1]].isEmpty());// target position isn't empty
        assertEquals(game.getBoardSquares()[pos[0]][pos[1]].getUnit(), attacker); // attacker hasn't moved
        assertEquals(game.getBoardSquares()[target[0]][target[1]].getUnit(), victim); // opponent hasn't moved
        assert(victim.getHealth() < victimHealth); // opponent health dropped
    }
    @Test
    void actionRecruit() { // BartSimpsonUnit recruits TomJerryUnit
        int[] pos = {0, 0}, target = {1, 1}; // recruit diagonally
        BoardSquare posSquare = game.getBoardSquares()[pos[0]][pos[1]];
        BoardSquare targetSquare = game.getBoardSquares()[target[0]][target[1]];
        ActionRecruit m = new ActionRecruit(game, pos[0], pos[1], target[0], target[1]);
        System.out.println(m.toString());
        assert(!posSquare.isEmpty());      // current position isn't empty
        assert(!targetSquare.isEmpty()); // target position isn't empty
        assert(posSquare.getUnit().getTeamColor() != targetSquare.getUnit().getTeamColor()); // not already teammates
        m.perfomAction();
        assert(posSquare.getUnit().getTeamColor() == targetSquare.getUnit().getTeamColor()); // now teammates
    }
}

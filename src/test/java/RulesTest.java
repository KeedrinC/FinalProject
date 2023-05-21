import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RulesTest {
    Game game;
    RulesTest() {
        Player one = new Player(1, new Team("red", new ArrayList<Unit>(Arrays.asList(new BartSimpsonUnit(), new TomJerryUnit()))));
        Player two = new Player(2, new Team("blue", new ArrayList<Unit>(Arrays.asList(new TomJerryUnit()))));
        game = new Game(2, 2, one, two); // 2x2 test environment
        game.getBoardSquares()[0][0].setUnit(one.getTeam().getTeamUnits().get(0));
        game.getBoardSquares()[0][1].setUnit(one.getTeam().getTeamUnits().get(1));
        game.getBoardSquares()[1][1].setUnit(two.getTeam().getTeamUnits().get(0));
    }
    @Test
    void checkMoveAction() {
        int[] pos = {0, 0}, target = {1, 0}, target2 = {0, 1}, target3 = {1, 1};
        assertTrue(Rules.checkValidAction(game, pos[0], pos[1], target[0], target[1], 'M'));
        assertFalse(Rules.checkValidAction(game, pos[0], pos[1], target2[0], target2[1], 'M'));
        assertFalse(Rules.checkValidAction(game, pos[0], pos[1], target3[0], target3[1], 'M'));
    }
    @Test
    void checkAttackAction() { // TomJerryUnit attacks BartSimpsonUnit
        int[] pos = {0, 1}, target = {1, 1}; // attack downward
        assertTrue(Rules.checkValidAction(game, pos[0], pos[1], target[0], target[1], 'A'));
        // fail if not the current player's turn
        assertFalse(Rules.checkValidAction(game, target[0], target[1], pos[0], pos[1], 'A'));
    }
    @Test
    void checkRecruitAction() { // TomJerryUnit attacks BartSimpsonUnit
        int[] pos = {0, 0}, target = {1, 1}; // recruit diagonally
        assertTrue(Rules.checkValidAction(game, pos[0], pos[1], target[0], target[1], 'R'));
    }
    @Test
    void checkSpawnAction() {
        int[] pos = {0, 0}, target = {1, 1}; // recruit diagonally
        assertTrue(Rules.checkValidAction(game, pos[0], pos[1], target[0], target[1], 'R'));
    }
}

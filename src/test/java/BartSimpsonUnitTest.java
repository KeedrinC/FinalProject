import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BartSimpsonUnitTest {

    @Test
    void testSpawn() {
        BartSimpsonUnit tempBart = new BartSimpsonUnit();
        assertTrue(tempBart.spawn() instanceof BartSimpsonUnit);
        assertTrue(tempBart.spawn() instanceof Unit);
    }

    @Test
    void getNumTimesSpawned() {
        BartSimpsonUnit noTimes = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        BartSimpsonUnit oneTime = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 1, true, true);
        assertEquals(noTimes.getNumTimesSpawned(), 0);
        assertEquals(oneTime.getNumTimesSpawned(), 1);
    }

    @Test
    void canDistract() {
        BartSimpsonUnit canDistract = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        BartSimpsonUnit cannotDistract = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 1, false, true);
        assertTrue(canDistract.canDistract());
        assertFalse(cannotDistract.canDistract());
    }

    @Test
    void canRecruit() {
        BartSimpsonUnit canRecruit = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        BartSimpsonUnit cannotRecruit = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 1, true, false);
        assertTrue(canRecruit.canRecruit());
        assertFalse(cannotRecruit.canRecruit());
    }

    @Test
    void canSpawn() {
        BartSimpsonUnit canSpawn = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        BartSimpsonUnit cannotSpawn = new BartSimpsonUnit('B', "Bart Simpson", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 2, true, true);
        assertTrue(canSpawn.canSpawn());
        assertFalse(cannotSpawn.canSpawn());
    }

    @Test
    void setDistract() {
        BartSimpsonUnit tempBart = new BartSimpsonUnit();
        assertTrue(tempBart.canDistract());
        tempBart.setDistract(false);
        assertFalse(tempBart.canDistract());
    }

    @Test
    void setRecruit() {
        BartSimpsonUnit tempBart = new BartSimpsonUnit();
        assertTrue(tempBart.canRecruit());
        tempBart.setRecruit(false);
        assertFalse(tempBart.canRecruit());
    }

    @Test
    void setNumTimesSpawned() {
        BartSimpsonUnit noTimes = new BartSimpsonUnit();
        assertEquals(noTimes.getNumTimesSpawned(), 0);
        noTimes.setNumTimesSpawned(1);
        assertEquals(noTimes.getNumTimesSpawned(), 1);
    }

    @Test
    void validMovePath() {
        BartSimpsonUnit bart = new BartSimpsonUnit();
        // Bart can move any number of spaces up and down the column, but no spaces across the row
        assert(bart.validMovePath(0, 0, 0, 1));
        assert(bart.validMovePath(0, 0, 0, 2));
        assert(bart.validMovePath(0, 0, 0, 3));
        assert(bart.validMovePath(0, 0, 0, 4));
        assert(bart.validMovePath(0, 0, 0, 100));
        assertFalse(bart.validMovePath(0, 0, 1, 1));
        assertFalse(bart.validMovePath(0, 0, 1, 2));
        assertFalse(bart.validMovePath(0, 0, 1, 3));
        assertFalse(bart.validMovePath(0, 0, 1, 4));
        // or two spaces across the row but no spaces down the column
        assert(bart.validMovePath(3, 0, 1, 0));
        assert(bart.validMovePath(3, 0, 2, 0));
        assert(bart.validMovePath(3, 0, 4, 0));
        assert(bart.validMovePath(3, 0, 5, 0));
        assertFalse(bart.validMovePath(3, 0, 0, 0));
        assertFalse(bart.validMovePath(3, 0, 6, 0));
    }
}
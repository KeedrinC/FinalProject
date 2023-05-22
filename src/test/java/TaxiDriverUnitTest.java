import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxiDriverUnitTest {

    @Test
    void testSpawn() {
        TaxiDriverUnit tempBart = new TaxiDriverUnit();
        assertTrue(tempBart.spawn() instanceof TaxiDriverUnit);
        assertTrue(tempBart.spawn() instanceof Unit);
    }

    @Test
    void getNumTimesSpawned() {
        TaxiDriverUnit noTimes = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        TaxiDriverUnit oneTime = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 1, true, true);
        assertEquals(noTimes.getNumTimesSpawned(), 0);
        assertEquals(oneTime.getNumTimesSpawned(), 1);
    }

    @Test
    void canDistract() {
        TaxiDriverUnit canDistract = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        TaxiDriverUnit cannotDistract = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 1, false, true);
        assertTrue(canDistract.canDistract());
        assertFalse(cannotDistract.canDistract());
    }

    @Test
    void canRecruit() {
        TaxiDriverUnit canRecruit = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        TaxiDriverUnit cannotRecruit = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 1, true, false);
        assertTrue(canRecruit.canRecruit());
        assertFalse(cannotRecruit.canRecruit());
    }

    @Test
    void canSpawn() {
        TaxiDriverUnit canSpawn = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 0, true, true);
        TaxiDriverUnit cannotSpawn = new TaxiDriverUnit('T', "Taxi Driver", 100.0, 0.0, 25.0, 0.0, 0,
			5, 5, 1, 0, 2, true, true);
        assertTrue(canSpawn.canSpawn());
        assertFalse(cannotSpawn.canSpawn());
    }

    @Test
    void setDistract() {
        TaxiDriverUnit tempBart = new TaxiDriverUnit();
        assertTrue(tempBart.canDistract());
        tempBart.setDistract(false);
        assertFalse(tempBart.canDistract());
    }

    @Test
    void setRecruit() {
        TaxiDriverUnit tempBart = new TaxiDriverUnit();
        assertTrue(tempBart.canRecruit());
        tempBart.setRecruit(false);
        assertFalse(tempBart.canRecruit());
    }

    @Test
    void setNumTimesSpawned() {
        TaxiDriverUnit noTimes = new TaxiDriverUnit();
        assertEquals(noTimes.getNumTimesSpawned(), 0);
        noTimes.setNumTimesSpawned(1);
        assertEquals(noTimes.getNumTimesSpawned(), 1);
    }

    @Test
    void validMovePath() {
        TaxiDriverUnit bart = new TaxiDriverUnit();
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
        assert(bart.validMovePath(3, 0, 1, 0));
        assert(bart.validMovePath(3, 0, 2, 0));
        assert(bart.validMovePath(3, 0, 4, 0));
        assert(bart.validMovePath(3, 0, 5, 0));
        assertFalse(bart.validMovePath(3, 0, 0, 0));
        assertFalse(bart.validMovePath(3, 0, 6, 0));
    }
}
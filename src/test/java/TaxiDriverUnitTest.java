import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaxiDriverUnitTest {

    @Test
    void testSpawn() {
        TaxiDriverUnit tempTaxi = new TaxiDriverUnit();
        assertTrue(tempTaxi.spawn() instanceof TaxiDriverUnit);
        assertTrue(tempTaxi.spawn() instanceof Unit);
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
        TaxiDriverUnit tempTaxi = new TaxiDriverUnit();
        assertTrue(tempTaxi.canDistract());
        tempTaxi.setDistract(false);
        assertFalse(tempTaxi.canDistract());
    }

    @Test
    void setRecruit() {
        TaxiDriverUnit tempTaxi = new TaxiDriverUnit();
        assertTrue(tempTaxi.canRecruit());
        tempTaxi.setRecruit(false);
        assertFalse(tempTaxi.canRecruit());
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
        TaxiDriverUnit taxi = new TaxiDriverUnit();
        // Taxi can move any number of spaces up and down the column, but no spaces across the row
        assert(taxi.validMovePath(0, 0, 1, 0));
        assert(taxi.validMovePath(0, 0, 2, 0));
        assert(taxi.validMovePath(0, 0, 3, 0));
        assert(taxi.validMovePath(0, 0, 0, 1));
        assert(taxi.validMovePath(0, 0, 0, 2));
        assert(taxi.validMovePath(0, 0, 0, 3));
        assert(taxi.validMovePath(0, 0, 1, 1));
        assert(taxi.validMovePath(0, 0, 2, 2));
        assert(taxi.validMovePath(0, 0, 3, 3));
        assertFalse(taxi.validMovePath(0, 0, 0, 4));
        assertFalse(taxi.validMovePath(0, 0, 2, 1));
    }
}
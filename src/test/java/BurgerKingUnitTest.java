import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BurgerKingUnitTest {
    @Test
    void testSpawn() {
        BurgerKingUnit tempKing = new BurgerKingUnit();
        assertTrue(tempKing.spawn() instanceof BurgerKingUnit);
        assertTrue(tempKing.spawn() instanceof Unit);
    }

    @Test
    void dealDamage() {
        BurgerKingUnit tempKing = new BurgerKingUnit();
        BurgerKingUnit highDamageKing = new BurgerKingUnit('K', "Burger King", 100.0, 0.0, 
            100, 0.0, 0, 5, 5, 1, 0, true);
        BurgerKingUnit noDamageKing = new BurgerKingUnit('K', "Burger King", 100.0, 0.0, 
            0, 0.0, 0, 5, 5, 1, 0, false);
        assertEquals(tempKing.dealDamage(), 20);
        assertEquals(highDamageKing.dealDamage(), 105);
        assertEquals(noDamageKing.dealDamage(), 0);
    }

    @Test
    void validAttackPath() {
        BurgerKingUnit bk = new BurgerKingUnit();
        assert(bk.validAttackPath(0, 0, 0, 1));
        assert(bk.validAttackPath(0, 0, 1, 0));
        assert(bk.validAttackPath(0, 0, 1, 1));
        assertFalse(bk.validAttackPath(0, 0, 2, 0));
        assertFalse(bk.validAttackPath(0, 0, 2, 1));
        assertFalse(bk.validAttackPath(0, 0, 2, 2));
        assertFalse(bk.validAttackPath(0, 0, 0, 2));
    }
}
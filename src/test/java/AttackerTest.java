import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AttackerTest {
    @Test
    void getNumRecruits() {
        Attacker tj = new TomJerryUnit();
        assertEquals(tj.getNumAttacks(), 0);
    }
    @Test
    void setNumRecruits() {
        Attacker tj = new TomJerryUnit();
        assertEquals(tj.getNumAttacks(), 0);
        tj.setNumAttacks(100);
        assertEquals(tj.getNumAttacks(), 100);
    }
}
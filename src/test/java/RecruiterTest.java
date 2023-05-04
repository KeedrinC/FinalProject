import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecruiterTest {
    @Test
    void getNumRecruits() {
        Recruiter bart = new BartSimpsonUnit();
        assertEquals(bart.getNumRecruits(), 0);
    }
    @Test
    void setNumRecruits() {
        Recruiter bart = new BartSimpsonUnit();
        assertEquals(bart.getNumRecruits(), 0);
        bart.setNumRecruits(100);
        assertEquals(bart.getNumRecruits(), 100);
    }
}
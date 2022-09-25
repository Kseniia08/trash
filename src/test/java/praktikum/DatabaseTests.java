package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class DatabaseTests {
    @Test
    public void testConstructor() {
        Database db = new Database();

        assertTrue(db.availableBuns().size() > 0);
    }

}

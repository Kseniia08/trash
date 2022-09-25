package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTests {
    @Test
    public void getNameTest(){
        Bun bun = new Bun("Rice", 1.22f);
        String expected = "Rice";
        String actual = bun.getName();
        assertEquals(actual, expected);
    }

    @Test
    public void getPriceTest(){
        Bun bun = new Bun("Rice", 1.22f);
        float expected = 1.22f;
        float actual = bun.getPrice();
        assertEquals("Error! Prise isn't similar", actual, expected, 0.00f);
    }
}

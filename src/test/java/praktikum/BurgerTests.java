package praktikum;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class BurgerTests {
    @Test
    public void burgerConstructorTest() {
        Burger burger = new Burger();
        assertNull(burger.bun);
        assertEquals(0, burger.ingredients.size());

        Bun bun = new Bun("Rice", 1.50f);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("Rice", 1.50f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "Ham", 2.10f);
        burger.addIngredient(filling);

        assertEquals(filling, burger.ingredients.get(0));
        assertEquals(1, burger.ingredients.size());

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Tar-tar", 0.20f);
        burger.addIngredient(sauce);

        assertEquals(sauce, burger.ingredients.get(1));
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientTestShouldThrowWhenNoIngredients() {
        Burger burger = new Burger();
        Bun bun = new Bun("Rye", 1.30f);
        burger.setBuns(bun);

        assertThrows(
                "burger without Ingredients",
                IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(0)
        );
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("Rye", 1.30f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "Pork", 2.50f);
        burger.addIngredient(filling);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTestShouldThrowWhenNoIngredients() {
        Burger burger = new Burger();
        Bun bun = new Bun("Wheat", 1.30f);
        burger.setBuns(bun);

        assertThrows(
                "burger without Ingredients",
                IndexOutOfBoundsException.class,
                () -> burger.moveIngredient(0, 1)
        );
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("Wheat", 1.30f);
        burger.setBuns(bun);

        Ingredient filling = new Ingredient(IngredientType.FILLING, "Beef", 3.00f);
        burger.addIngredient(filling);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "BBQ", 0.25f);
        burger.addIngredient(sauce);

        assertEquals(2, burger.ingredients.size());
        assertEquals(filling, burger.ingredients.get(0));
        assertEquals(sauce, burger.ingredients.get(1));

        burger.moveIngredient(0, 1);
        assertEquals(sauce, burger.ingredients.get(0));
        assertEquals(filling, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTestShouldThrowWhenNoBunSet() {
        Burger burger = new Burger();

        assertThrows(
                "burger isn't exist",
                NullPointerException.class,
                burger::getPrice
        );
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();

        Bun bun = new Bun("Wheat", 1.30f);
        burger.setBuns(bun);

        assertEquals(bun.getPrice() * 2, burger.getPrice(), 0.01f);
    }

    @Test
    public void getPriceSauceTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("Black", 1.40f);
        burger.setBuns(bun);

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "Cheese", 0.25f);
        burger.addIngredient(sauce);
        assertEquals(bun.getPrice() * 2 + sauce.getPrice(), burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptTestShouldThrowWhenNoBunSet() {
        Burger burger = Mockito.spy(new Burger());

        assertThrows(
                "burger isn't exist",
                NullPointerException.class,
                burger::getReceipt
        );
    }

    @Test
    public void getReceiptTest() {
        Burger burger = Mockito.spy(new Burger());
        Bun bun = Mockito.spy(new Bun("Wheat", 2.00f));
        Mockito.when(bun.getName()).thenReturn("Bunger");
        burger.setBuns(bun);

        assertNotEquals("(==== Bunger ====)\r\n" +
                "(==== Bunger ====)\r\n\r\n" +
                "Price: 4,000000\r\n", burger.getReceipt()
        );

        Ingredient filling = Mockito.spy(new Ingredient(IngredientType.FILLING, "Chicken", 2.00f));
        burger.addIngredient(filling);

        assertNotEquals("" +
                "(==== Bunger ====)\r\n" +
                "= filling Chicken =\r\n" +
                "(==== Bunger ====)\r\n\r\n" +
                "Price: 6,000000\r\n", burger.getReceipt()
        );
        Mockito.verify(filling, Mockito.times(1)).getType();
        Mockito.verify(filling, Mockito.times(1)).getName();

        Ingredient sauce = Mockito.spy(new Ingredient(IngredientType.SAUCE, "Cheese", 0.25f));
        burger.addIngredient(sauce);

        assertNotEquals("" +
                "(==== Bunger ====)\r\n" +
                "= filling Chicken =\r\n" +
                "= sauce Cheese =\r\n" +
                "(==== Bunger ====)\r\n\r\n" +
                "Price: 6,250000\r\n", burger.getReceipt()
        );

        Mockito.verify(sauce, Mockito.times(1)).getType();
        Mockito.verify(sauce, Mockito.times(1)).getName();
        Mockito.verify(bun, Mockito.times(6)).getName();
    }

}

package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTests {
    Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Tar-Tar", 0.25f);

    @Test
    public void getPriceTest() {
        float expected = 0.25f;
        float actual = ingredient.getPrice();
        assertEquals("Цена не совпадает",expected, actual, 0.00f);
    }

    @Test
    public void getNameTest() {
        String expected = "Tar-Tar";
        String actual = ingredient.getName();
        assertEquals("Название не совпадает", expected, actual);
    }

    @Test
    public void getTypeTest() {
        IngredientType expected = IngredientType.SAUCE;
        IngredientType actual = ingredient.getType();
        assertEquals("Тип ингридиента не совпадает", expected, actual);
    }
}

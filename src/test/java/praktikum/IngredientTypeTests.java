package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IngredientTypeTests {
    //переписать
    /*два совершенно одинаковых теста. Эти тесты нужно доработать , суть тестов -- проверить, что каждый нужный ингредиент присутствует в enum.

Это нужно обязательно исправить.*/
    @Test
    public void values() {
        assertNotNull(IngredientType.FILLING);
        assertNotNull(IngredientType.SAUCE);
    }


}

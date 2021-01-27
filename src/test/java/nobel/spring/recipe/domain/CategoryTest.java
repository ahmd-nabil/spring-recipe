package nobel.spring.recipe.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }
    @Test
    void getId() {
        Long idActualValue = 2l;

        category.setId(idActualValue);

        assertEquals(2, idActualValue);
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}
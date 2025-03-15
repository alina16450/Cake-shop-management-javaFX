package Filters;

import Domain.Cake;
import Filter.CakeTypeFilter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CakeTypeFilterTest {
    @Test
    void testAccept_ValidType() {
        CakeTypeFilter filter = new CakeTypeFilter("Chocolate");
        Cake cake = new Cake(1, "Chocolate", 10.0);
        assertTrue(filter.accept(cake));
    }

    @Test
    void testAccept_DifferentType() {
        CakeTypeFilter filter = new CakeTypeFilter("Vanilla");
        Cake cake = new Cake(1, "Chocolate", 10.0);
        assertFalse(filter.accept(cake));
    }

    @Test
    void testAccept_NullCake() {
        CakeTypeFilter filter = new CakeTypeFilter("Chocolate");
        assertFalse(filter.accept(null));
    }

    @Test
    void testAccept_CaseInsensitiveMatch() {
        CakeTypeFilter filter = new CakeTypeFilter("chocolate");
        Cake cake = new Cake(1,"Chocolate", 10.0);
        assertTrue(filter.accept(cake));
    }
}

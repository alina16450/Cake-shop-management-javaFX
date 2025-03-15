package Filters;

import Domain.Cake;
import Filter.CakePriceFilter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CakePriceFilterTest {
    @Test
    void testAccept_ValidPrice() {
        CakePriceFilter filter = new CakePriceFilter(10.0);
        Cake cake = new Cake(2,"Vanilla", 9.5);
        assertTrue(filter.accept(cake));
    }

    @Test
    void testAccept_PriceEqualsLimit() {
        CakePriceFilter filter = new CakePriceFilter(10.0);
        Cake cake = new Cake(1, "Chocolate", 10.0);
        assertTrue(filter.accept(cake));
    }

    @Test
    void testAccept_InvalidPrice() {
        CakePriceFilter filter = new CakePriceFilter(10.0);
        Cake cake = new Cake(3, "Strawberry", 11.0);
        assertFalse(filter.accept(cake));
    }

    @Test
    void testConstructor_NegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CakePriceFilter(-5.0);
        });
    }
}


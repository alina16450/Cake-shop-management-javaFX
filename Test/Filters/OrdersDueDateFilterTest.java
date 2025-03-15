package Filters;

import Domain.Cake;
import Domain.Date;
import Domain.Orders;
import Filter.OrdersDueDateFilter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdersDueDateFilterTest {
    @Test
    void testAccept_ValidDueDate() {
        Date filterDate = new Date(20, 5);
        OrdersDueDateFilter filter = new OrdersDueDateFilter(filterDate);
        Orders order = new Orders(1, "Order 1", new Date(10, 5), new Date(18, 5), new Cake(1,"Chocolate", 10.0));
        assertTrue(filter.accept(order));
    }

    @Test
    void testAccept_DueDateEqualsFilter() {
        Date filterDate = new Date(20, 5);
        OrdersDueDateFilter filter = new OrdersDueDateFilter(filterDate);
        Orders order = new Orders(1, "Order 2", new Date(10, 5), new Date(20, 5), new Cake(2,"Vanilla", 9.0));
        assertTrue(filter.accept(order));
    }

    @Test
    void testAccept_InvalidDueDate() {
        Date filterDate = new Date(20, 5);
        OrdersDueDateFilter filter = new OrdersDueDateFilter(filterDate);
        Orders order = new Orders(2, "Order 3", new Date(10, 5), new Date(22, 5), new Cake(3,"Strawberry", 12.0));
        assertFalse(filter.accept(order));
    }
}

package Filters;

import Domain.Cake;
import Domain.Date;
import Domain.Orders;
import Filter.OrdersReceivedDateFilter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdersReceivedDateFilterTest {
    @Test
    void testAccept() {
        Date filterDate = new Date(10, 5);
        OrdersReceivedDateFilter filter = new OrdersReceivedDateFilter(filterDate);
        Orders order = new Orders(1, "Test Order", new Date(15, 5), new Date(20, 5), new Cake(1,"Chocolate", 1.5));
        assertTrue(filter.accept(order));
    }

    @Test
    void testEquals() {
        Date filterDate = new Date(10, 5);
        OrdersReceivedDateFilter filter = new OrdersReceivedDateFilter(filterDate);
        Orders order = new Orders(1, "Test", new Date(15, 5), new Date(10, 5), new Cake(1,"Chocolate", 1.5));
        assertTrue(filter.equals(filter));
    }

    @Test
    void testReject(){
        Date filterDate = new Date(10, 5);
        OrdersReceivedDateFilter filter = new OrdersReceivedDateFilter(filterDate);
        Orders order = new Orders(1, "Test", new Date(9, 5), new Date(10, 5), new Cake(1,"Chocolate", 1.5));
    }
}


package Service;

import Domain.Cake;
import Domain.Date;
import Domain.Orders;
import Filter.CakePriceFilter;
import Filter.OrdersReceivedDateFilter;
import Repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

public class ServiceTest {
    private Service service;
    private Repository<Integer, Cake> cakeRepository;
    private Repository<Integer, Orders> orderRepository;

    @BeforeEach
    void setUp() {
        cakeRepository = new Repository<Integer, Cake>();
        orderRepository = new Repository<Integer, Orders>();
        service = new Service(cakeRepository, orderRepository);
    }

    @Test
    void testAddCake() {
        service.addCake(1, "Chocolate", 10.0);
        assertEquals("Chocolate", service.getCake(1).getType());
        assertEquals(10.0, service.getCake(1).getPrice());
    }

    @Test
    void testAddOrder() {
        Cake cake = new Cake(1, "Vanilla", 15.0);
        service.addCake(1, "Vanilla", 15.0);
        service.addOrder(1, "Order1", new Date(10, 5), new Date(20, 5), cake);
        assertEquals("Order1", service.getOrder(1).getName());
    }

    @Test
    void testDeleteCake() {
        service.addCake(1, "Strawberry", 12.0);
        service.deleteCake(1);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getCake(1);
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testDeleteOrder() {
        Cake cake = new Cake(1, "Vanilla", 15.0);
        service.addCake(1, "Vanilla", 15.0);
        service.addOrder(1, "Order1", new Date(10, 5), new Date(20, 5), cake);
        service.deleteOrder(1);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getOrder(1);
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testModifyCake() {
        service.addCake(1, "Strawberry", 12.0);
        service.modifyCake(1, "Chocolate", 14.0);
        assertEquals("Chocolate", service.getCake(1).getType());
        assertEquals(14.0, service.getCake(1).getPrice());
    }

    @Test
    void testModifyOrder() {
        Cake cake = new Cake(1, "Vanilla", 15.0);
        service.addCake(1, "Vanilla", 15.0);
        service.addOrder(1, "Order1", new Date(10, 5), new Date(20, 5), cake);
        service.modifyOrder(1, "Updated Order", new Date(12, 6), new Date(22, 6), cake);
        assertEquals("Updated Order", service.getOrder(1).getName());
    }

    @Test
    void testFilterCakesByPrice() {
        service.addCake(1, "Chocolate", 10.0);
        service.addCake(2, "Vanilla", 15.0);
        List<Cake> filteredCakes = service.filterCakes(new CakePriceFilter(12.0));
        assertEquals(1, filteredCakes.size());
        assertEquals("Chocolate", filteredCakes.get(0).getType());
    }

    @Test
    void testFilterOrdersByReceivedDate() {
        Cake cake = new Cake(1, "Vanilla", 15.0);
        service.addCake(1, "Vanilla", 15.0);
        service.addOrder(1, "Order1", new Date(10, 5), new Date(20, 5), cake);
        service.addOrder(2, "Order2", new Date(15, 6), new Date(25, 6), cake);
        List<Orders> filteredOrders = service.filterOrders(new OrdersReceivedDateFilter(new Date(12, 5)));
        assertEquals(1, filteredOrders.size());
        assertEquals("Order2", filteredOrders.getFirst().getName());
    }
}

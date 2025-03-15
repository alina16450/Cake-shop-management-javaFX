package Domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdersTest {
    private Orders order;
    private Cake cake;
    private Date receivedDate;
    private Date dueDate;

    @BeforeEach
    void setUp() {
        cake = new Cake(1,"Chocolate", 1.5);
        receivedDate = new Date(10, 5);
        dueDate = new Date(20, 5);
        order = new Orders(1, "Birthday Cake", receivedDate, dueDate, cake);
    }

    @Test
    void testConstructor_ValidParameters() {
        assertEquals(1, order.getId());
        assertEquals("Birthday Cake", order.getName());
        assertEquals(receivedDate, order.getReceivedDate());
        assertEquals(dueDate, order.getDueDate());
        assertEquals(cake, order.getCake());
    }

    @Test
    void testConstructor_InvalidId() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Orders(-1, "Invalid Order", receivedDate, dueDate, cake);
        });
        assertEquals("Order id cannot be less than zero", exception.getMessage());
    }

    @Test
    void testConstructor_InvalidName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Orders(2, "", receivedDate, dueDate, cake);
        });
        assertEquals("You must provide a name for the order", exception.getMessage());
    }

    @Test
    void testSetReceivedDate() {
        Date newDate = new Date(15, 6);
        order.setReceivedDate(newDate);
        assertEquals(newDate, order.getReceivedDate());
    }

    @Test
    void testSetDueDate() {
        Date newDate = new Date(25, 6);
        order.setDueDate(newDate);
        assertEquals(newDate, order.getDueDate());
    }

}


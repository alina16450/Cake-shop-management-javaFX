package Domain;
import Domain.Cake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CakeTest {

    @Test
    void testGetId() {
        Cake cake = new Cake(1, "Chocolate", 20.0);
        assertEquals(1, cake.getId());
    }

    @Test
    void testSetId() {
        Cake cake = new Cake(0, "Chocolate", 20.0);
        cake.setId(5);
        assertEquals(5, cake.getId());
    }

    @Test
    void testGetType() {
        Cake cake = new Cake(1, "Chocolate", 20.0);
        assertEquals("Chocolate", cake.getType());
    }

    @Test
    void testSetType() {
        Cake cake = new Cake(1, "Vanilla", 20.0);
        cake.setType("Strawberry");
        assertEquals("Strawberry", cake.getType());
    }

    @Test
    void testGetPrice() {
        Cake cake = new Cake(1, "Chocolate", 20.0);
        assertEquals(20.0, cake.getPrice());
    }

    @Test
    void testSetPrice() {
        Cake cake = new Cake(1, "Vanilla", 15.0);
        cake.setPrice(25.0);
        assertEquals(25.0, cake.getPrice());
    }

    @Test
    void testEquals() {
        Cake cake1 = new Cake(1, "Chocolate", 20.0);
        Cake cake2 = new Cake(1, "Chocolate", 20.0);
        Cake cake3 = new Cake(2, "Vanilla", 25.0);
        assertEquals(cake1, cake2);
        assertNotEquals(cake1, cake3);
    }

    @Test
    void testHashCode() {
        Cake cake1 = new Cake(1, "Chocolate", 20.0);
        Cake cake2 = new Cake(1, "Chocolate", 20.0);
        Cake cake3 = new Cake(2, "Vanilla", 25.0);

        // Same hash code for equal objects
        assertEquals(cake1.hashCode(), cake2.hashCode());

        // Different hash code for different objects
        assertNotEquals(cake1.hashCode(), cake3.hashCode());
    }

    @Test
    void testToString() {
        Cake cake = new Cake(1, "Chocolate", 20.0);
        String expected = " id:1, Chocolate cake, $20.0";
        assertEquals(expected, cake.toString());
    }

    @Test
    void testCompareTo() {
        Cake cake1 = new Cake(1, "Chocolate", 20.0);
        Cake cake2 = new Cake(2, "Vanilla", 25.0);
        Cake cake3 = new Cake(3, "Chocolate", 30.0);

        assertTrue(cake1.compareTo(cake2) < 0); // Chocolate < Vanilla
        assertTrue(cake2.compareTo(cake1) > 0); // Vanilla > Chocolate
        assertEquals(0, cake1.compareTo(cake3)); // Same type
    }
}


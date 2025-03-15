package Domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    @Test
    void testConstructor_ValidDate() {
        Date date = new Date(15, 6);
        assertEquals(15, date.getDay());
        assertEquals(6, date.getMonth());
    }

    @Test
    void testConstructor_InvalidMonth() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date(10, 13);
        });
        assertEquals("Invalid month", exception.getMessage());
    }

    @Test
    void testConstructor_InvalidDay() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date(32, 5);
        });
        assertEquals("Invalid day", exception.getMessage());
    }

    @Test
    void testSetDay_Valid() {
        Date date = new Date(10, 5);
        date.setDay(20);
        assertEquals(20, date.getDay());
    }

    @Test
    void testSetDay_Invalid() {
        Date date = new Date(10, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            date.setDay(32);
        });
        assertEquals("Invalid day", exception.getMessage());
    }

    @Test
    void testSetMonth_Valid() {
        Date date = new Date(10, 5);
        date.setMonth(8);
        assertEquals(8, date.getMonth());
    }

    @Test
    void testSetMonth_Invalid() {
        Date date = new Date(10, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            date.setMonth(13);
        });
        assertEquals("Invalid month", exception.getMessage());
    }

    @Test
    void testIsEmpty() {
        Date date = new Date(0, 0);
        assertTrue(date.isEmpty());
    }

    @Test
    void testToString() {
        Date date = new Date(10, 5);
        assertEquals("10.5", date.toString());
    }
}
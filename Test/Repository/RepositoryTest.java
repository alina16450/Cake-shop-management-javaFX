package Repository;

import Domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.NoSuchElementException;

public class RepositoryTest {
    private Repository<Integer, MockEntity> repository;

    @BeforeEach
    void setUp() {
        repository = new Repository<Integer, MockEntity>();
    }

    @Test
    void testAdd_ValidEntity() {
        MockEntity entity = new MockEntity(1, "Test Entity");
        repository.add(1, entity);
        assertEquals(entity, repository.findById(1));
    }

    @Test
    void testAdd_DuplicateEntity() {
        MockEntity entity = new MockEntity(1, "Test Entity");
        repository.add(1, entity);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.add(1, new MockEntity(1, "Another Entity"));
        });
        assertEquals("Entity already exists", exception.getMessage());
    }

    @Test
    void testDelete_ValidEntity() {
        MockEntity entity = new MockEntity(1, "Test Entity");
        repository.add(1, entity);
        repository.delete(1);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.findById(1);
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testDelete_NonExistentEntity() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.delete(1);
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testModify_ValidEntity() {
        MockEntity entity = new MockEntity(1, "Old Name");
        repository.add(1, entity);
        repository.modify(1, new MockEntity(1, "New Name"));
        assertEquals("New Name", repository.findById(1).getName());
    }

    @Test
    void testModify_NonExistentEntity() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.modify(1, new MockEntity(1, "Modified Name"));
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testFindById_ValidEntity() {
        MockEntity entity = new MockEntity(1, "Test Entity");
        repository.add(1, entity);
        assertEquals(entity, repository.findById(1));
    }

    @Test
    void testFindById_NonExistentEntity() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.findById(1);
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testContains_ValidEntity() {
        MockEntity entity = new MockEntity(1, "Test Entity");
        repository.add(1, entity);
        assertTrue(repository.contains(1));
    }

    @Test
    void testContains_NonExistentEntity() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            repository.contains(1);
        });
        assertEquals("Entity does not exist", exception.getMessage());
    }

    @Test
    void testFindAll() {
        MockEntity entity1 = new MockEntity(1, "Entity One");
        MockEntity entity2 = new MockEntity(2, "Entity Two");
        repository.add(1, entity1);
        repository.add(2, entity2);
        assertEquals(2, ((Collection<?>) repository.findAll()).size());
    }
}

class MockEntity implements Identifiable<Integer> {
    private Integer id;
    private String name;

    public MockEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}

package Repository;

import Domain.Identifiable;

import java.util.HashMap;
import java.util.Iterator;

public class Repository<ID, T extends Identifiable<ID>> implements IRepository<ID, T> {
    //The class that handles implementing our CRUD operations for the console. It uses a hashmap that has as Id our Identifiable class from Domain, and a generic type T that will be replaced by either our Cake or Orders class.
    protected HashMap<ID, T> map = new HashMap<>();

    @Override
    public void add(ID id, T elem) {
        if (map.containsKey(id)) throw new RuntimeException("Entity already exists");
        map.put(id, elem);}

    @Override
    public void delete(ID id) {
        if (!map.containsKey(id)) throw new RuntimeException("Entity does not exist");
        map.remove(id);
    }

    @Override
    public void modify(ID id, T elem) {
        if(!map.containsKey(id)) throw new RuntimeException("Entity does not exist");
        map.replace(id, elem);
    }

    @Override
    public T findById(ID id) {
        if(!map.containsKey(id)) throw new RuntimeException("Entity does not exist");
        return map.get(id);
    }

    @Override
    public boolean contains(ID id) {
        if (!map.containsKey(id)) throw new RuntimeException("Entity does not exist");
        return map.containsKey(id);
    }

    @Override
    public Iterable<T> findAll() {
        return map.values();
    }

    @Override
    public Iterator<T> iterator() {
        return map.values().iterator();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}

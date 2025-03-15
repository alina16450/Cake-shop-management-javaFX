package Repository;

import Domain.Identifiable;
import Domain.Orders;

import java.util.Iterator;

public interface IRepository<ID, T extends Identifiable<ID>> {

    public void add(ID id, T elem) throws IllegalArgumentException;

    public void delete(ID id) throws IllegalArgumentException;

    public void modify(ID id, T elem) throws IllegalArgumentException;

    public T findById(ID id) throws IllegalArgumentException;

    public boolean contains(ID id) throws IllegalArgumentException;

    public Iterable<T> findAll();

    Iterator<T> iterator();

    public boolean isEmpty();
}

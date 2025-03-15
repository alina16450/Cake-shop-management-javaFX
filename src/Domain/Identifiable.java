package Domain;

import java.io.Serializable;

public interface Identifiable<T> extends Serializable {
    //all of our Domain classes will implement this Identifiable interface, to ensure each class instance is identified in the same way.
    public T getId();
    public void setId(T id);
}

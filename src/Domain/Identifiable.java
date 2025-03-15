package Domain;

import java.io.Serializable;

public interface Identifiable<T> extends Serializable {
    public T getId();
    public void setId(T id);
}

package Filter;

import Domain.Cake;
import Domain.Orders;

public class CakeTypeFilter implements Filter<Cake> {
    protected String type;

    public CakeTypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean accept(Cake cake) {
        return cake != null && cake.getType().toLowerCase().contains(type.toLowerCase());
    }
}

package Filter;

import Domain.Cake;

public class CakePriceFilter implements Filter<Cake> {
    protected double price;

    public CakePriceFilter(double price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }

    @Override
    public boolean accept(Cake cake) {
        return cake.getPrice() <= price;
    }
}

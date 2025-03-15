package Filter;

import Domain.Date;
import Domain.Orders;

public class OrdersReceivedDateFilter implements Filter<Orders> {
    protected Date receivedDate;

    public OrdersReceivedDateFilter(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public boolean accept(Orders orders) {
        if (orders.getReceivedDate() == null) {
            return false;
        }
        Date orderDate = orders.getReceivedDate();

        if (orderDate.getMonth() > receivedDate.getMonth()) {
            return true;
        }

        if (orderDate.getMonth() == receivedDate.getMonth()) {
            return orderDate.getDay() >= receivedDate.getDay();
        }

        return false;
    }
}

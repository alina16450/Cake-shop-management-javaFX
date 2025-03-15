package Filter;

import Domain.Date;
import Domain.Orders;

public class OrdersDueDateFilter implements Filter<Orders> {
    protected Date dueDate;

    public OrdersDueDateFilter(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean accept(Orders orders) {
        //return ((orders.getDueDate().getMonth() == dueDate.getMonth()) && (orders.getDueDate().getDay() <= dueDate.getDay())) || (orders.getDueDate().getMonth() <= dueDate.getMonth());
        if (orders.getDueDate().getMonth() == dueDate.getMonth() ) {
            return (orders.getDueDate().getDay() <= dueDate.getDay());
        }
        if (orders.getDueDate().getMonth() <= dueDate.getMonth()) {
            return true;
        }
        return false;
    }
}


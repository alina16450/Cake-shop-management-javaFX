package Service;

import Domain.Cake;
import Domain.Date;
import Domain.Orders;
import Filter.*;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Service {
    protected IRepository<Integer, Cake> cakes;
    protected IRepository<Integer, Orders> orders;

    public Service(IRepository<Integer, Cake> cakes, IRepository<Integer, Orders> orders) {
        this.cakes = cakes;
        this.orders = orders;
    }

    public void addCake(Integer id, String type, Double price) {
        Cake cake = new Cake(id, type, price);
        cakes.add(id, cake);
    }

    public void addOrder(Integer id, String name, Date date1, Date date2, Cake cake) {
        Orders order = new Orders(id, name, date1, date2, cake);
        orders.add(id, order);
    }

    //Since a cake type is being deleted, we also remove it from any active order.
    public void deleteCake(Integer id) {
        Cake c = cakes.findById(id);
        if (!orders.isEmpty()) {
            List<Orders> ord = new ArrayList<>();
            for (Orders o : orders.findAll()) {
                if (Objects.equals(o.getCake().getId(), c.getId())) {
                    ord.add(o);
                }
            }
            for (Orders o : ord) {
                o.setCake(null);
            }
        }
        cakes.delete(id);
    }

    public void deleteOrder(Integer id) {
        orders.delete(id);
    }

    //since a cake type is modified, we also change it in any active order.
    public void modifyCake(Integer id, String type, double price) {
        Cake cake = new Cake(id, type, price);
        cakes.modify(id, cake);
        if (!orders.isEmpty()) {
            List<Orders> ord = new ArrayList<>();
            for (Orders o : orders.findAll()) {
                if (Objects.equals(o.getCake().getId(), cake.getId())) {
                    ord.add(o);
                }
            }
            for (Orders o : ord) {
                o.setCake(cake);
            }
        }
    }

    public void modifyOrder(Integer id, String name, Date date1, Date date2, Cake cake) {
        Orders order = new Orders(id, name, date1, date2, cake);
        orders.modify(id, order);
    }

    public Iterable<Cake> getCakes() {
        return cakes.findAll();
    }

    public Iterable<Orders> getOrders() {
        return orders.findAll();
    }

    public Cake getCake(Integer id) {
        return cakes.findById(id);
    }

    public Orders getOrder(Integer id) {
        return orders.findById(id);
    }

    public boolean containsCake(Integer id) {
        return cakes.contains(id);
    }

    public List<Cake> cakeToList(){
        List<Cake> cake = new ArrayList<>();
        for(Cake c : cakes.findAll()){
            cake.add(c);
        }
        return cake;
    }
    public List<Orders> ordersToList(){
        List<Orders> order = new ArrayList<>();
        for(Orders o : orders.findAll()){
            order.add(o);
        }
        return order;
    }

    //Generic method to filter cakes based on any given filter.
    public List<Cake> filterCakes(Filter<Cake> filter) {
        List<Cake> filteredList = new ArrayList<>();

        for (Cake c : cakes.findAll()) {
            if (filter.accept(c)) {
                filteredList.add(c);
            }
        }
        return filteredList;
    }

    //Generic method to filter orders based on any given filter.
    public List<Orders> filterOrders(Filter<Orders> filter) {
        List<Orders> filteredList = new ArrayList<>();
        for( Orders o : orders.findAll() ) {
            if (filter.accept(o)) {
                filteredList.add(o);
            }
        }
        return filteredList;
    }

    //Filter cakes by price (cakes with price ≤ given price).
    public List<Cake> filterCakesByPrice(double price) {
        List<Cake> filteredCakes = filterCakes(new CakePriceFilter(price));
        for (Cake c : filteredCakes) {
            System.out.println(c);
        }
        return filteredCakes;
    }

    //Filter cakes by a given type.
    public List<Cake> filterCakesByType(String type) {
        List<Cake> filteredCakes = filterCakes(new CakeTypeFilter(type));
        for (Cake c : filteredCakes) {
            System.out.println(c);
        }
        return filteredCakes;
    }

    //Filter orders by received date (orders received on or after a given date).
    public List<Orders> filterOrdersByReceivedDate(Date receivedDate) {
        List<Orders> filterOrders = filterOrders(new OrdersReceivedDateFilter(receivedDate));
        for (Orders o : filterOrders) {
            System.out.println(o);
        }
        return filterOrders;
    }

    //Filter orders by due date (orders due on or before a given date).
    public List<Orders> filterOrdersByDueDate(Date dueDate) {
        List<Orders> filteredOrders = filterOrders(new OrdersDueDateFilter(dueDate));
        for (Orders o : filteredOrders) {
            System.out.println(o);
        }
        return filteredOrders;
    }
}

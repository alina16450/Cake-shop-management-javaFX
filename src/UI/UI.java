package UI;

import Domain.Cake;
import Domain.Date;
import Service.Service;
import Domain.Orders;
import java.util.Scanner;


public class UI {
    private Service service;

    public UI(Service service) {
        this.service = service;
    }

    public void showMenu() {
        System.out.println("1. Show all cakes.");
        System.out.println("2. Add cake.");
        System.out.println("3. Edit cake.");
        System.out.println("4. Delete cake.");
        System.out.println("5. Show all orders.");
        System.out.println("6. Add order.");
        System.out.println("7. Edit order.");
        System.out.println("8. Delete order.");
        System.out.println("9. Filter cakes by maximum price: ");
        System.out.println("10. Filter cakes by a type: ");
        System.out.println("11. Filter orders received after a given date: ");
        System.out.println("12. Filter orders due before a given date: ");
        System.out.println("0. Exit");
    }

    public void run() {
        while (true) {
            showMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose an option: ");
            int option = scanner.nextInt();
            if (option == 0) return;
            switch (option) {
                case 1: {
                    System.out.println("The available cakes are: ");
                    Iterable<Cake> cakes = service.getCakes();
                    for (Cake cake : cakes) {
                        System.out.println(cake);
                    }
                }
                break;
                case 2: {
                    try {
                        System.out.println("Enter the id of the new cake: ");
                        int id = scanner.nextInt();
                        System.out.println("Enter the type of the new cake: ");
                        String type = scanner.next();
                        System.out.println("Enter the price of the new cake: ");
                        Double price = Double.valueOf(scanner.next());

                        service.addCake(id, type, price);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 3: {
                    try {
                        System.out.println("Enter the id of the cake to modify: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the type of the cake or press enter to skip: ");
                        String type = scanner.nextLine();
                        if (type.isEmpty()) {
                            type = service.getCake(id).getType();
                        }
                        System.out.println("Enter the price of the cake or press enter to skip: ");
                        String p = scanner.nextLine();
                        double price = service.getCake(id).getPrice();
                        if (!p.isEmpty()) {
                            price = Double.parseDouble(p);
                        }
                        service.modifyCake(id, type, price);
                    }
                    catch (Exception e) {
                    System.out.println(e.getMessage());}
                }
                    break;
                case 4: {
                    try {
                        System.out.println("Enter the id of the cake to delete: ");
                        int id = scanner.nextInt();
                        service.deleteCake(id);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 5: {
                    System.out.println("The current orders are: ");
                    Iterable<Orders> orders = service.getOrders();
                    for (Orders order : orders) {
                        System.out.println(order);
                    }
                }
                break;
                case 6: {
                    try {
                        System.out.println("Enter the id of the new order: ");
                        int id = scanner.nextInt();
                        System.out.println("Enter the name on the new order: ");
                        String name = scanner.next();
                        System.out.println("Enter the day the new order is received: ");
                        String day1 = scanner.next();
                        int d1 = Integer.parseInt(day1);
                        System.out.println("Enter the month the new order is received: ");
                        String month = scanner.next();
                        int m1 = Integer.parseInt(month);
                        Date date1 = new Date(d1, m1);
                        System.out.println("Enter the day the new order is due: ");
                        String day2 = scanner.next();
                        int d2 = Integer.parseInt(day2);
                        System.out.println("Enter the month the new order is due: ");
                        String month2 = scanner.next();
                        int m2 = Integer.parseInt(month2);
                        Date date2 = new Date(d2, m2);
                        System.out.println("Enter the cake: What is the cake id?");
                        int cakeId = scanner.nextInt();

                        if (!service.containsCake(cakeId)) {
                            System.out.println("Cake does not exist. Try again.");
                        } else {
                            service.addOrder(id, name, date1, date2, service.getCake(cakeId));
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 7: {
                    try {
                        System.out.println("Enter the id of the new order to modify: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter the new name on the order or press enter to skip: ");
                        String name = scanner.nextLine();
                        if (name.isEmpty()) {
                            name = service.getOrder(id).getName();
                        }
                        System.out.println("Enter the new received day of the order or press enter to skip: ");
                        String day1 = scanner.nextLine();
                        int d1 = Integer.parseInt(day1);
                        System.out.println("Enter the new received month of the order or press enter to skip: ");
                        String month = scanner.nextLine();
                        int m1 = Integer.parseInt(month);
                        Date date1 = new Date(d1, m1);
                        if (date1.isEmpty()) {
                            date1 = service.getOrder(id).getReceivedDate();
                        }
                        System.out.println("Enter the day of new due date of the order or press enter to skip: ");
                        String day2 = scanner.nextLine();
                        int d2 = Integer.parseInt(day2);
                        System.out.println("Enter the new month of the order or press enter to skip: ");
                        String month2 = scanner.nextLine();
                        int m2 = Integer.parseInt(month2);
                        Date date2 = new Date(d2, m2);
                        if (date2.isEmpty()) {
                            date2 = service.getOrder(id).getDueDate();
                        }
                        service.modifyOrder(id, name, date1, date2, service.getCake(id));
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 8: {
                    try {
                        System.out.println("Enter the id of the order to delete: ");
                        int id = scanner.nextInt();
                        service.deleteOrder(id);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 9: {
                    try{
                        System.out.println("Enter max price: $");
                        double max = Double.parseDouble(scanner.next());
                        for (Cake c : service.filterCakesByPrice(max)){
                            System.out.println(c);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 10: {
                    try{
                        System.out.println("Enter cake type: ");
                        String type = scanner.next();
                        for (Cake c : service.filterCakesByType(type)){
                            System.out.println(c);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 11: {
                    try{
                        System.out.println("Enter received day of the order: ");
                        int day = scanner.nextInt();
                        System.out.println("Enter received month of the order: ");
                        int month = scanner.nextInt();
                        Date date = new Date(day, month);
                        for (Orders o : service.filterOrdersByReceivedDate(date)){
                            System.out.println(o);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
                case 12: {
                    try{
                        System.out.println("Enter due day of the order: ");
                        int day = scanner.nextInt();
                        System.out.println("Enter due month of the order: ");
                        int month = scanner.nextInt();
                        Date date = new Date(day, month);
                        for (Orders o : service.filterOrdersByDueDate(date)){
                            System.out.println(o);
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;

            }
        }
    }
}



package GUI;
import Domain.*;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller<T> {
    private final Service service;
    private ObservableList<Cake> cakes;
    private ObservableList<Orders> orders;

    @FXML
    private ListView<T> list;
    @FXML
    private ToggleButton Cakes, Orders;

    @FXML
    private ComboBox<String> combos, filters;

    @FXML
    private ToggleGroup toggles;

    @FXML
    private TextField cakeId, type, price, orderId, name, filter1, filter2;

    @FXML
    private Button submit, submit1;

    @FXML
    private Label messages;

    @FXML
    private DatePicker receivedDate, dueDate;

    public Controller(Service service) {
        this.service = service;
    }

    public void initialize(){
        List<Cake> cake = (List<Cake>) service.cakeToList();
        cakes =  FXCollections.observableList(cake);
        List<Orders> order = (List<Orders>) service.ordersToList();
        orders =  FXCollections.observableList(order);

        List<String> options  = new ArrayList<>();
        options.add("Add");
        options.add("Remove");
        options.add("Modify");
        combos.setItems(FXCollections.observableList(options));
        combos.setPromptText("Select option");
        combos.setOnAction(this::getOption);
        submit.setOnAction(this::addObject);
        toggles.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getOption(null);  // Force update of input fields
            }
        });
        List<String> cakeFilter = new ArrayList<>();
        List<String> orderFilter = new ArrayList<>();
        cakeFilter.add("cake max price");
        cakeFilter.add("cake type");
        orderFilter.add("orders received after");
        orderFilter.add("orders due before");
        toggles.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ToggleButton selectedToggle = (ToggleButton) newValue;

                if (selectedToggle.getText().equals("Cakes")) {
                    filters.setItems(FXCollections.observableList(cakeFilter));
                    filters.setPromptText("Select cake filter");
                    System.out.println("Cakes filter options updated.");
                }
                else if (selectedToggle.getText().equals("Orders")) {
                    filters.setItems(FXCollections.observableList(orderFilter));
                    filters.setPromptText("Select order filter");
                    System.out.println("Orders filter options updated.");
                }
            } else {
                System.out.println("No toggle selected.");
            }
        });
        filters.setOnAction(this::showFilterFields);
        submit1.setOnAction(this::getFilters);

    }

    public void reset() {
        ToggleButton selectedToggle = (ToggleButton) toggles.getSelectedToggle();
        if(selectedToggle.getText().equals("Cakes")) {
            cakeId.clear();
            type.clear();
            price.clear();
        }
        else if(selectedToggle.getText().equals("Orders")) {
            orderId.clear();
            name.clear();
            dueDate.setValue(LocalDate.now());
            receivedDate.setValue(LocalDate.now());
            cakeId.clear();
        }
        else {
            System.out.println("Error: No category selected.");
            messages.setText("Error: No category selected.");
        }
    }

    public void displayAll(ActionEvent event) {
        list.getItems().clear();
        if (event.getSource() == Cakes) {
            List<Cake> cakeList  = service.cakeToList();
            cakes = FXCollections.observableArrayList(cakeList);
            list.setItems((ObservableList<T>) cakes);
            list.refresh();
        }
        if (event.getSource() == Orders) {
            List<Orders> ordersList = service.ordersToList();
            orders = FXCollections.observableList(ordersList);
            list.setItems((ObservableList<T>) orders);
            list.refresh();
        }
    }

    public void getOption(ActionEvent event) {
        String option = combos.getValue(); // Get the selected action

        // Ensure an action is selected
        if (option == null) {
            System.out.println("Error: No option selected.");
            hideAllFields();  // Hide fields if no option is selected
            return;
        }

        // Get the selected toggle (Cakes or Orders)
        ToggleButton selectedToggle = (ToggleButton) toggles.getSelectedToggle();

        if (selectedToggle == null) {
            System.out.println("Error: No category selected.");
            hideAllFields();
            return;
        }

        // Hide all fields before showing the correct ones
        hideAllFields();

        switch (option) {
            case "Add" -> {
                if (selectedToggle == Cakes) {
                    cakeId.setVisible(true);
                    type.setVisible(true);
                    price.setVisible(true);
                    resetCakes();
                    System.out.println("Displaying cake fields for adding.");
                } else if (selectedToggle == Orders) {
                    orderId.setVisible(true);
                    name.setVisible(true);
                    receivedDate.setVisible(true);
                    dueDate.setVisible(true);
                    cakeId.setVisible(true);
                    resetOrders();
                    System.out.println("Displaying order fields for adding.");
                }
            }
            case "Remove" -> hideAllFields();
            case "Modify" -> {
                if (selectedToggle == Cakes) {
                    type.setVisible(true);
                    price.setVisible(true);
                    resetCakes();
                    System.out.println("Displaying cake fields for modifying.");
                } else if (selectedToggle == Orders) {
                    name.setVisible(true);
                    receivedDate.setVisible(true);
                    dueDate.setVisible(true);
                    cakeId.setVisible(true);
                    resetOrders();
                    System.out.println("Displaying order fields for modifying.");
                }
            }
            default -> {
                System.out.println("Error: No option selected.");
                hideAllFields();
            }
        }
    }

    public void resetCakes(){
        cakeId.setPromptText("Cake Id:");
        type.setPromptText("Type:");
        price.setPromptText("Price:");
    }

    public void resetOrders(){
        orderId.setPromptText("Order Id:");
        name.setPromptText("Name:");
        receivedDate.setPromptText("Received Date:");
        dueDate.setPromptText("Due Date:");
        cakeId.setPromptText("Cake Id:");
    }

    private void hideAllFields() {
        cakeId.setVisible(false);
        type.setVisible(false);
        price.setVisible(false);
        orderId.setVisible(false);
        name.setVisible(false);
        receivedDate.setVisible(false);
        dueDate.setVisible(false);
    }

    public void addObject(ActionEvent event) {
        ToggleButton selectedToggle = (ToggleButton) toggles.getSelectedToggle();

        if (selectedToggle == null) {
            System.out.println("No category selected!");
            return;
        }

        // Validate that an action has been selected
        if (combos.getValue() == null) {
            System.out.println("No action selected!");
            return;
        }

        if ("Add".equals(combos.getValue())) {
            if (selectedToggle == Cakes) {
                // Validate input fields before parsing
                if (cakeId.getText().isEmpty() || type.getText().isEmpty() || price.getText().isEmpty()) {
                    System.out.println("Please fill all cake fields!");
                    messages.setText("Please fill all cake fields!");
                    return;
                }
                try {
                    int id = Integer.parseInt(cakeId.getText());
                    String t = type.getText();
                    double p = Double.parseDouble(price.getText());

                    Cake c = new Cake(id, t, p);
                    if (cakes == null) {
                        cakes = FXCollections.observableArrayList();
                    }
                    cakes.add(c);
                    service.addCake(id, t, p);
                    list.setItems((ObservableList<T>) cakes);
                    list.refresh();

                    resetCakes();
                    reset();

                    System.out.println("Cake added successfully!");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for cake fields!");
                    messages.setText("Invalid input for cake fields!");
                }
            } else if (selectedToggle == Orders) {
                // Validate input fields before parsing
                if (orderId.getText().isEmpty() || name.getText().isEmpty() || cakeId.getText().isEmpty() ||
                        receivedDate.getValue() == null || dueDate.getValue() == null) {
                    System.out.println("Please fill all order fields!");
                    messages.setText("Please fill all order fields!");
                    return;
                }
                try {
                    int id = Integer.parseInt(orderId.getText());
                    String n = name.getText();
                    LocalDate received = receivedDate.getValue();
                    LocalDate due = dueDate.getValue();
                    int ci = Integer.parseInt(cakeId.getText());
                    Date date1 = new Date(received.getDayOfMonth(), received.getMonthValue());
                    Date date2 = new Date(due.getDayOfMonth(), due.getMonthValue());
                    Cake cake = service.getCake(ci);
                    resetOrders();

                    if (cake == null) {
                        System.out.println("Cake with given ID does not exist!");
                        messages.setText("Cake with given ID does not exist!");
                        return;
                    }

                    Orders order = new Orders(id, n, date1, date2, cake);
                    if (orders == null) {
                        orders = FXCollections.observableArrayList();
                    }
                    orders.add(order);
                    service.addOrder(id, n, date1, date2, cake);
                    list.setItems((ObservableList<T>) orders);
                    list.refresh();
                    reset();
                    System.out.println("Order added successfully!");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for order fields!");
                    messages.setText("Invalid input for order fields!");
                }
            }
        }
        else if ("Remove".equals(combos.getValue())) {
            if (selectedToggle == Cakes) {
                Cake cake = (Cake) list.getSelectionModel().getSelectedItem();
                if (cake == null) {
                    System.out.println("Error: No cake selected for removal.");
                    return;
                }
                cakes.remove(cake);
                service.deleteCake(cake.getId());
                list.setItems((ObservableList<T>) cakes);
                list.refresh();
                System.out.println("Cake removed successfully!");
            }
            if (selectedToggle == Orders) {
                Orders order = (Orders) list.getSelectionModel().getSelectedItem();
                if  (order == null) {
                    System.out.println("Error: No order selected for removal.");
                    return;
                }
                orders.remove(order);
                service.deleteOrder(order.getId());
                list.setItems((ObservableList<T>) orders);
                list.refresh();
                System.out.println("Order removed successfully!");
            }
        }
        else if ("Modify".equals(combos.getValue())) {
            if (selectedToggle == Cakes) {
                Cake cake = (Cake) list.getSelectionModel().getSelectedItem();
                if (cake == null) {
                    System.out.println("Error: No cake selected for removal.");
                    messages.setText("No cake selected for removal.");
                    return;
                }
                try {
                    int id = cake.getId();
                    String t = cake.getType();
                    double p = cake.getPrice();

                    if (!type.getText().isEmpty())
                        t = type.getText().trim();
                    if (!price.getText().isEmpty())
                        p = Double.parseDouble(price.getText().trim());

                    Cake c = new Cake (id, t, p);
                    service.modifyCake(id, t, p);
                    cakes.set(id-1, c);
                    list.setItems((ObservableList<T>) cakes);
                    list.refresh();
                    resetCakes();
                    reset();

                    System.out.println("Cake added successfully!");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for cake fields!");
                    messages.setText("Invalid input for cake fields!");
                }
            }
            else if (selectedToggle == Orders) {
                Orders order = (Orders) list.getSelectionModel().getSelectedItem();
                if (order == null) {
                    System.out.println("Error: No order selected for removal.");
                    messages.setText("No order selected for removal.");
                }
                try{
                    assert order != null;
                    int id = order.getId();
                    String n = order.getName();
                    int rd = order.getReceivedDate().getDay();
                    int  rm = order.getReceivedDate().getMonth();
                    int dd = order.getDueDate().getDay();
                    int dm = order.getDueDate().getMonth();
                    int cakeid = order.getCake().getId();

                    if(!name.getText().isEmpty())
                        n = name.getText().trim();
                    if(receivedDate.getValue() != null) {
                        rd = Integer.parseInt(String.valueOf(receivedDate.getValue().getDayOfMonth()));
                        rm = Integer.parseInt(String.valueOf(receivedDate.getValue().getMonthValue()));
                    }
                    if(dueDate.getValue() != null) {
                        dd = Integer.parseInt(String.valueOf(dueDate.getValue().getDayOfMonth()));
                        dm = Integer.parseInt(String.valueOf(dueDate.getValue().getMonthValue()));
                    }
                    if(!cakeId.getText().isEmpty())
                        cakeid = Integer.parseInt(cakeId.getText().trim());
                    Date date1 = new Date(rd, rm);
                    Date date2 = new Date(dd, dm);
                    Orders o = new Orders(order.getId(), n, date1, date2, service.getCake(cakeid));
                    service.modifyOrder(id, n, date1, date2, service.getCake(cakeid));
                    orders.set(id-1, o);
                    list.setItems((ObservableList<T>) orders);
                    list.refresh();
                    resetOrders();
                    reset();
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input for order fields!");
                }
            }
            else{
                System.out.println("Invalid input for order fields!");
            }
        }
    }

    public void hideFilterFields() {
        filter1.setVisible(false);
        filter2.setVisible(false);
    }

    public void showFilterFields(ActionEvent event) {
        String option = filters.getValue();
        if (option == null) {
            System.out.println("Error: No option selected.");
            hideFilterFields();
        }
        else if (option.equals("cake max price")) {
            filter1.setVisible(true);
            filter1.setPromptText("Price:");
        }
        else if (option.equals("cake type")) {
            filter1.setVisible(true);
            filter1.setPromptText("Type:");
        }
        else if (option.equals("orders received after")) {
            filter1.setVisible(true);
            filter1.setPromptText("Received Day:");
            filter2.setVisible(true);
            filter2.setPromptText("Received Month:");
        }
        else if (option.equals("orders due before")) {
            filter1.setVisible(true);
            filter1.setPromptText("Due Day:");
            filter2.setVisible(true);
            filter2.setPromptText("Due Month:");
        }
        else{
            System.out.println("Error: Invalid input for order fields!");
            hideFilterFields();
        }
    }

    public void getFilters(ActionEvent event) {
        String option = filters.getValue();
        switch (option) {
            case "cake max price" -> {
                try {
                    if (filter1.getText().isEmpty()) {
                        System.out.println("Please enter the price to filter by.");
                        messages.setText("Please enter the price to filter by.");
                        return;
                    }
                    double price = Double.parseDouble(filter1.getText().trim());
                    if (price < 0) {
                        messages.setText("Price cannot be negative.");
                        throw new IllegalArgumentException("Price cannot be negative!");
                    }
                    List<T> filtered = (List<T>) service.filterCakesByPrice(price);
                    ObservableList<Cake> c = (ObservableList<Cake>) FXCollections.observableList(filtered);
                    list.setItems((ObservableList<T>) c);

                } catch (NumberFormatException e) {
                    System.err.println("Invalid input for price fields!");
                }
                hideFilterFields();
            }
            case "cake type" -> {
                try {
                    String type = filter1.getText().trim();
                    if (type.isEmpty()) {
                        messages.setText("Type cannot be empty!");
                        System.out.println("Type cannot be empty!");
                        return;
                    }
                    List<T> filtered = (List<T>) service.filterCakesByType(type);
                    ObservableList<Cake> c = (ObservableList<Cake>) FXCollections.observableList(filtered);
                    list.setItems((ObservableList<T>) c);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input for type fields!");
                }
                hideFilterFields();
            }
            case "orders received after" -> {
                try {
                    if ((filter1.getText().isEmpty()) || (filter2.getText().isEmpty())) {
                        System.out.println("Please enter the received Day to filter by.");
                        messages.setText("Please enter the received Day to filter by.");
                        return;
                    }

                    int rd = Integer.parseInt(filter1.getText().trim());
                    int rm = Integer.parseInt(filter2.getText().trim());
                    Date date1 = new Date(rd, rm);
                    List<T> filtered = (List<T>) service.filterOrdersByReceivedDate(date1);
                    ObservableList<Orders> o = (ObservableList<Orders>) FXCollections.observableList(filtered);
                    list.setItems((ObservableList<T>) o);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input for received Day fields!");
                }
                hideFilterFields();
            }
            case "orders due before" -> {
                try {
                    if ((filter1.getText().isEmpty()) || (filter2.getText().isEmpty())) {
                        System.out.println("Please enter the received Day to filter by.");
                        messages.setText("Please enter the received Day to filter by.");
                        return;
                    }
                    int dd = Integer.parseInt(filter1.getText().trim());
                    int dm = Integer.parseInt(filter2.getText().trim());
                    Date date1 = new Date(dd, dm);
                    List<T> filtered = (List<T>) service.filterOrdersByDueDate(date1);
                    ObservableList<Orders> o = (ObservableList<Orders>) FXCollections.observableList(filtered);
                    list.setItems((ObservableList<T>) o);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input for Due Day fields!");
                }
                hideFilterFields();
            }
            default -> System.out.println("Invalid input for order fields!");
        }
    }
}

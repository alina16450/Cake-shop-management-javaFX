package Repository;

import Domain.Cake;
import Domain.Date;
import Domain.Orders;

import java.io.*;
import java.util.Iterator;

public class TextFileRepoOrders extends FileRepo<Integer, Orders>{

    public TextFileRepoOrders(String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 9) {
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int receviedday = Integer.parseInt(parts[2]);
                int receivedmonth = Integer.parseInt(parts[3]);
                int dueday = Integer.parseInt(parts[4]);
                int duemonth = Integer.parseInt(parts[5]);
                int cakeid = Integer.parseInt(parts[6]);
                String type = parts[7];
                double price = Double.parseDouble(parts[8]);

                Date receivedDate = new Date (receviedday, receivedmonth);
                Date dueDate = new Date (dueday, duemonth);
                Cake cake = new Cake(cakeid, type, price);
                Orders order = new Orders(id, name, receivedDate, dueDate, cake);
                super.add(id, order);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Iterator<Orders> it = super.iterator();
            while (it.hasNext()) {
                Orders order = it.next();
                bw.write(order.toString());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

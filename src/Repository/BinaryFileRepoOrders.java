package Repository;

import Domain.Orders;

import java.io.*;
import java.util.HashMap;

public class BinaryFileRepoOrders extends FileRepo<Integer, Orders> {

    public BinaryFileRepoOrders(String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.map = (HashMap<Integer, Orders>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.map);
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

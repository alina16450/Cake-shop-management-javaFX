package Repository;

import Domain.Cake;

import java.io.*;
import java.util.HashMap;

public class BinaryFileRepoCake extends FileRepo <Integer, Cake> {

    public BinaryFileRepoCake(String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.map = (HashMap<Integer, Cake>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.map);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

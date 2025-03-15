package Repository;

import Domain.Cake;

import java.io.*;
import java.util.Iterator;

public class TextFileRepoCake extends FileRepo<Integer, Cake> {

    public TextFileRepoCake(String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line=br.readLine()) != null ){
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    continue;
                }
                int id = Integer.parseInt(parts[0]);
                String type = parts[1];
                double price = Double.parseDouble(parts[2]);
                Cake cake = new Cake(id, type, price);
                super.add(id, cake);
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            Iterator<Cake> it = super.iterator();
            while (it.hasNext()) {
                Cake cake = it.next();
                bw.write(cake.toString());
            }
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

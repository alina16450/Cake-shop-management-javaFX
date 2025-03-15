package Main;

import Domain.Cake;
import Domain.Orders;
import Repository.*;
import Service.Service;
import UI.UI;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Main {
    public static void main(String[] args) {
        Properties prop = new Properties();
        IRepository<Integer, Cake> cakesRepository = null;
        IRepository<Integer, Orders> ordersRepository = null;
        try{
            prop.load(new FileReader("settings.properties"));

            String repoType = prop.getProperty("Repository");
            String location = prop.getProperty("Location");
            String cakePath = prop.getProperty("Cake");
            String ordersPath = prop.getProperty("Orders");

            if (repoType.equals("memory")) {
                cakesRepository = new Repository<Integer, Cake>();
                ordersRepository = new Repository<Integer, Orders>();
            }
            if (repoType.equals("text")) {
                cakesRepository = new TextFileRepoCake(location +"/"+ cakePath+".txt");
                ordersRepository = new TextFileRepoOrders(location +"/"+ ordersPath+".txt");
            }
            if (repoType.equals("binary")) {
                cakesRepository = new BinaryFileRepoCake(location +"/"+ cakePath+".bin");
                ordersRepository = new BinaryFileRepoOrders(location +"/"+ ordersPath+".bin");
            }
            if  (repoType.equals("database")) {
                cakesRepository = new DBRepoCake("identifier.sqlite", "Cake");
                ordersRepository =  new DBRepoOrders("identifier.sqlite", "Orders");
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Service service = new Service(cakesRepository, ordersRepository);


        UI ui = new UI(service);
        ui.run();
        }
}
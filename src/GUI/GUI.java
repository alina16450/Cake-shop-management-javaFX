package GUI;

import Domain.Cake;
import Domain.Orders;
import Repository.*;
import Service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cakeShop.fxml"));
        Properties prop = new Properties();

        IRepository<Integer, Cake> cakeRepo = null;
        IRepository<Integer, Orders> ordersRepo = null;

        try {
            prop.load(new FileReader("settings.properties"));
            String repoType = prop.getProperty("Repository");
            String location = prop.getProperty("Location");
            String cakePath = prop.getProperty("Cake");
            String ordersPath = prop.getProperty("Orders");

            if (repoType.equals("memory")) {
                cakeRepo = new Repository<Integer, Cake>();
                ordersRepo = new Repository<Integer, Orders>();
            }
            if (repoType.equals("text")) {
                cakeRepo = new TextFileRepoCake(location + "/" + cakePath + ".txt");
                ordersRepo = new TextFileRepoOrders(location + "/" + ordersPath + ".txt");
            }
            if (repoType.equals("binary")) {
                cakeRepo = new BinaryFileRepoCake(location + "/" + cakePath + ".bin");
                ordersRepo = new BinaryFileRepoOrders(location + "/" + ordersPath + ".bin");
            }
            if (repoType.equals("database")) {
                cakeRepo = new DBRepoCake("identifier.sqlite", "Cake");
                ordersRepo = new DBRepoOrders("identifier.sqlite", "Orders");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Service service = new Service(cakeRepo, ordersRepo);

        Controller controller = new Controller(service);

        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

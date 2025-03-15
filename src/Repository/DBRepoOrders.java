package Repository;

import Domain.Cake;
import Domain.Orders;
import Domain.Date;

import java.sql.*;

public class DBRepoOrders extends DBRepo<Integer, Orders>{

    public DBRepoOrders(String URL, String tableName) {
        super(URL, tableName);
    }

    @Override
    void readFromDB() {
        String query = "SELECT * FROM " + tableName;
        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(query)){
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int orderId = rs.getInt(1);
                    String name = rs.getString(2);
                    int receivedDay =  rs.getInt(3);
                    int receivedMonth = rs.getInt(4);
                    int dueDay = rs.getInt(5);
                    int dueMonth = rs.getInt(6);
                    int cakeId = rs.getInt(7);
                    String type = rs.getString(8);
                    double price = rs.getDouble(9);
                    Date date1 = new Date(receivedDay, receivedMonth);
                    Date date2 = new Date(dueDay, dueMonth);

                    Cake cake = new Cake(cakeId, type, price);
                    if (map.containsKey(orderId)){
                        throw new RuntimeException(orderId + " already exists");
                    }
                    Orders orders = new Orders(orderId, name, date1, date2, cake);
                    map.put(orderId, orders);
                }
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToDB(Orders elem) {
        String query = "INSERT INTO " + tableName + "(Id, Name, receivedDay, receivedMonth, dueDay, dueMonth, cakeId, Type, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = DriverManager.getConnection(URL);
        PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, elem.getId());
            ps.setString(2, elem.getName());
            ps.setInt(3, elem.getReceivedDate().getDay());
            ps.setInt(4, elem.getReceivedDate().getMonth());
            ps.setInt(5, elem.getDueDate().getDay());
            ps.setInt(6, elem.getDueDate().getMonth());
            ps.setInt(7, elem.getCake().getId());
            ps.setString(8, elem.getCake().getType());
            ps.setDouble(9, elem.getCake().getPrice());
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    void deleteFromDB(Integer id) {
        String query = "DELETE FROM " + tableName + " WHERE id = ?";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}

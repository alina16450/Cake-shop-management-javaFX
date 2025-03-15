package Repository;

import Domain.Cake;

import java.sql.*;

public class DBRepoCake extends DBRepo<Integer, Cake> {

    public DBRepoCake(String URL, String tableName){
        super(URL, tableName);
    }

    @Override
    void readFromDB(){
        String query = "SELECT * FROM " + tableName;
        try (Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query)){
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int  id = rs.getInt("cakeId");
                    String type = rs.getString("Type");
                    double price = rs.getDouble("Price");
                    Cake  cake = new Cake(id, type, price);
                    if(map.containsKey(id)){
                        throw new RuntimeException("Entity already exists");
                    }
                    map.put(id, cake);
                }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToDB(Cake elem){
        String query = "INSERT INTO " + tableName + " (cakeId, Type, Price) VALUES (?, ?, ?)";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query))
            {
                ps.setInt(1, elem.getId());
                ps.setString(2, elem.getType());
                ps.setDouble(3, elem.getPrice());
                ps.executeUpdate();
            }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    void deleteFromDB(Integer id){
        String query = "DELETE FROM " + tableName + " WHERE cakeId = ?";
        try(Connection con = DriverManager.getConnection(URL);
            PreparedStatement ps = con.prepareStatement(query))
            {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

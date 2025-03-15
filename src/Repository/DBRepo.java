package Repository;

import Domain.Identifiable;

import java.sql.SQLException;

public abstract class DBRepo<ID, T extends Identifiable<ID>> extends Repository<ID, T> {
    public String URL;
    public String tableName;

    public DBRepo(String repoPath, String tableName) {
        this.URL = "jdbc:sqlite:" + repoPath;
        this.tableName = tableName;
        readFromDB();
    }

    abstract void readFromDB();

    abstract void writeToDB(T elem);

    abstract void deleteFromDB(ID id);

    @Override
    public void add(ID id, T elem) {
        super.add(id, elem);
        writeToDB(elem);
    }

    @Override
    public void delete(ID id) {
        super.delete(id);
        deleteFromDB(id);
    }

    @Override
    public void modify(ID id, T elem){
        super.modify(id, elem);
        deleteFromDB(id);
        writeToDB(elem);
    }

}

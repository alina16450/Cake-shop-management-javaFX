package Repository;

import Domain.Identifiable;

public abstract class FileRepo<ID, T extends Identifiable<ID>> extends Repository<ID, T> {
    //The generic abstract class that handles setup for the Text file and Binary file setup.
    protected String filename;

    public FileRepo(String filename) {
        this.filename = filename;
        readFromFile();
    }

    abstract void readFromFile();

    abstract void writeToFile();

    @Override
    public void add (ID id, T elem){
        super.add(id, elem);
        writeToFile();
    }

    @Override
    public void delete (ID id) {
        super.delete(id);
        writeToFile();
    }

    @Override
    public void modify (ID id, T elem) {
        super.modify(id, elem);
        writeToFile();
    }
}

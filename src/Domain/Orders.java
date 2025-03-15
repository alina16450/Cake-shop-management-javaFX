package Domain;

public class Orders implements Identifiable<Integer>, Comparable<Orders>{
    protected Integer id;
    protected String name;
    protected Date receivedDate;
    protected Date dueDate;
    protected Cake cake;

    public Orders(int id, String name, Date receivedDate, Date dueDate, Cake cake) {
        if(id < 0){
            throw new IllegalArgumentException("Order id cannot be less than zero");
        }
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("You must provide a name for the order");
        }
        this.id = id;
        this.name = name;
        this.receivedDate = receivedDate;
        this.dueDate = dueDate;
        this.cake = cake;
    }

    @Override
    public Integer getId() {return id;}

    @Override
    public void setId(Integer id) {
        if (id < 0){
            throw new IllegalArgumentException("Order id cannot be less than zero");
        }
        this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("You must provide a name for the order");
        }
        this.name = name;}

    public Date getReceivedDate() {return receivedDate;}

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getDueDate() {return dueDate;}

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Cake getCake() {return cake;}

    public void setCake(Cake cake) {this.cake = cake;}

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", receivedDate='" + receivedDate.toString() + '\'' +
                ", dueDate='" + dueDate.toString() + '\'' +
                ", cake=" + cake +
                '}';
    }

    public int compareTo(Orders o) {
        return o.name.compareTo(this.name);
    }
}

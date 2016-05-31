package mainAppPackage;
import java.io.Serializable;
public  class ProductG4 implements Serializable{
    private int id;
    private String name;
    private double price;
    private long qty;
    private String date;
    private String des;

    public ProductG4() {
    }

    public ProductG4(int id, String name, double price, long qty, String date, String des) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.date = date;
        this.des=des;
    }

    public int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    } 
}

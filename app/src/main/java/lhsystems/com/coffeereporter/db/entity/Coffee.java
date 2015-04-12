package lhsystems.com.coffeereporter.db.entity;

/**
 * Created by Marcin on 2015-03-29.
 */
public class Coffee implements Entity {

    public static final String[] COLUMNS=  {"id","name","price"};
    public static final String TABLE_NAME = "COFFEES";

    private long id;
    private String name;
    private float price;

    public Coffee(){}

    public Coffee(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getTableName() {
        return Coffee.TABLE_NAME;
    }

    @Override
    public String[] getColumns() {
        return Coffee.COLUMNS;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coffee coffee = (Coffee) o;

        if (id != coffee.id) return false;
        if (Float.compare(coffee.price, price) != 0) return false;
        if (name != null ? !name.equals(coffee.name) : coffee.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

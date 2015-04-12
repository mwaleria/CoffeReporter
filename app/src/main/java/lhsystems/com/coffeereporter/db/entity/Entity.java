package lhsystems.com.coffeereporter.db.entity;

/**
 * Created by Marcin on 2015-03-31.
 */
public interface Entity {

    public long getId();
    public void setId(long id);
    public String getTableName();
    public String[] getColumns();
}

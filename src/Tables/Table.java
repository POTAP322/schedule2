package Tables;

public interface Table {
    void save();
    void load();
    void add(String... params);
    String getTableName();
}

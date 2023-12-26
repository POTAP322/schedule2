package Tables;

public interface Table {
    void save();
    void load();
    void add(String... params);

    int size();
    String getTableName();


}

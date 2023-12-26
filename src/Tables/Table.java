package Tables;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Table {
    void save();
    void load();
    void add(String... params);

    int size();
    String getTableName();


}

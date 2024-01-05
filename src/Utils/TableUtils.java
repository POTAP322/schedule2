package Utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class TableUtils {

    //для поиска свободного id в списке
    public static <T> int generateNewId(List<T> items, Function<T, Integer> idExtractor) { //вторым полем принимает лямбда выражение, которое например принимает студента и возвращает его studentId: student -> student.getStudentId()
        List<Integer> ids = new ArrayList<>();
        for (T item : items) {
            ids.add(idExtractor.apply(item));
        }
        Collections.sort(ids);
        for (int i = 0; i < ids.size(); i++) {
            if (i + 1 != ids.get(i)) {
                return i + 1;
            }
        }
        return ids.size() + 1;
    }

}

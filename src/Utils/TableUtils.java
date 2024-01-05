package Utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class TableUtils {

    //для поиска свободного id в списке
    public static <T> int generateNewId(List<T> items, Function<T, Integer> idExtractor) { //вторым полем принимает лямбда выражение, которое например принимает студента и возвращает его studentId: student -> student.getStudentId()
        List<Integer> indexes = new ArrayList<>();
        for (T item : items) {
            indexes.add(idExtractor.apply(item));
        }
        Collections.sort(indexes);
        for (int i = 0; i < indexes.size(); i++) {
            if (i + 1 != indexes.get(i)) {
                return i + 1;
            }
        }
        return indexes.size() + 1;
    }

}

package Tables;

import TableElements.Lesson;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Table {
    void save();
    void load();
    void add(String... params) throws Exception;

    void removeById(int id);


    int size();
    String getTableName();


    void addNew(int groupId, int subjectId,Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime firstLesson) throws Exception;
}

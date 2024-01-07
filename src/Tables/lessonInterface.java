package Tables;

import TableElements.Group;
import TableElements.Lesson;
import TableElements.Subject;

public interface lessonInterface extends Table{
    void addNewByGroupAndCourse(Group group, Subject subject, Lesson.LessonDay day, Lesson.LessonTime time);
    void addNew(int groupId, int subjectId, Lesson.LessonDay day, Lesson.LessonTime time);
}

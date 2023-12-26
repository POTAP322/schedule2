import TableElements.*;
import Tables.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataBase {
    private List<Table> tables;

    public DataBase() {
        tables = new ArrayList<>();
        tables.add(new GroupTable());
        tables.add(new CourseTable());
        tables.add(new StudentTable());
        tables.add(new TeacherTable());
        tables.add(new LessonTable());
        tables.add(new GroupLessonTable());

    }



    public Table getTableByName(String name) {
        for (int i = 0; i < tables.size(); i++) {
            if (Objects.equals(tables.get(i).getTableName(), name)) {
                return tables.get(i);
            }
        }
        return null;
    }



    public void saveAll() { //все таблицы сохраняем
        for (Table table : tables) {
            table.save();
        }
    }

    public void loadAll() { //все таблицы загружаем
        for (Table table : tables) {
            table.load();
        }

    }
    public List<Student> getStudentsByGroupId(int groupId){
        StudentTable studentTable = (StudentTable) getTableByName(StudentTable.name);
        List<Student> students = new ArrayList<>();
        for (Student student:studentTable) {
            if (student.getGroupId() == groupId){
                students.add(student);
            }
        }

        return students;
    }
    public List<Group> getGroupsByCourseId(int courseId){
        GroupTable groupTable = (GroupTable) getTableByName(GroupTable.name);
        List<Group> groups = new ArrayList<>();
        for (Group group:groupTable) {
            if (group.getCourseId() == courseId){
                groups.add(group);
            }
        }
        return groups;
    }
    public Integer getCourseIdByGroupId(int groupId){
        GroupTable groupTable = (GroupTable) getTableByName(GroupTable.name);
        for (Group group:groupTable) {
            if(group.getGroupId() == groupId){
                return group.getCourseId();
            }

        }
        return null;
    }
//    public Integer getEducationYearByGroupId(int groupId){
//        CourseTable courseTable = (CourseTable) getTableByName(CourseTable.name);
//        for (Course course:courseTable){
//            if (course.)
//        }
//    }
    public Integer getCourseYearByCourseId(int courseId){
        CourseTable courseTable = (CourseTable) getTableByName(CourseTable.name);
        for (Course course:courseTable) {
            if(course.getCourseId() == courseId){
                return course.getEducationYear();
            }
        }
        return null;
    }
    public List<Lesson> getLessonsByEducationYear(int educationYear){
        LessonTable lessonTable = (LessonTable) getTableByName(LessonTable.name);
        List<Lesson> lessons = new ArrayList<>();
        for (Lesson lesson:lessonTable) {
            if (lesson.getEducationYear() == educationYear){
                lessons.add(lesson);
            }

        }
        return lessons;
    }
    public String getLessonNameByLessonId(int lessonId){
        LessonTable lessonTable = (LessonTable) getTableByName(LessonTable.name);
        for (Lesson lesson:lessonTable) {
            if (lesson.getLessonId() == lessonId){
                return lesson.getSubjectName();
            }

        }
        return null;
    }
    public List<GroupLesson> getLessonsScheduleForGroupId(int groupId){
        GroupLessonTable groupLessonTable = (GroupLessonTable) getTableByName(GroupLessonTable.name);
        List<GroupLesson> groupLessons = new ArrayList<>();
        for (GroupLesson groupLesson:groupLessonTable){
            if (groupLesson.getGroupId()==groupId){
                groupLessons.add(groupLesson);
            }
        }
        return groupLessons;
    }

    public String getTeacherNameByTeacherId(int teacherId){
        TeacherTable teacherTable = (TeacherTable) getTableByName(TeacherTable.name);
        for (Teacher teacher:teacherTable){
            if(teacher.getTeacherId()==teacherId){
                return teacher.getName() + " " + teacher.getSurname();
            }
        }
        return null;
    }

}

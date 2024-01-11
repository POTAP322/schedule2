import TableElements.Lesson;

public class Main {

    public static void main(String[] args) throws Exception {
       DataBase dataBase = new DataBase();

        dataBase.loadAll();
        //махинации проводим тута

        //dataBase.getTableByName("StudentTable").add("Романчиииик","Романоооов","4");
        //dataBase.getTableByName("StudentTable").removeById(5);
        //dataBase.getTableByName("LessonTable").addNew;
        dataBase.getTableByName("LessonTable").addNew(5,5,Lesson.TypeOfWeek.secondType, Lesson.LessonDay.Friday, Lesson.LessonTime.firstLesson);
        //dataBase.getTableByName("LessonTable").add("1","3","fsfsd","8:34");

        for (int i = 0; i < dataBase.getStudentsByGroupId(1).size(); i++) {
            System.out.println(dataBase.getStudentsByGroupId(1).get(i).getSurname());

        }
        //System.out.println(dataBase.getCourseIdByGroupId(2));
        //int a = dataBase.getCourseIdByGroupId(2);
        //System.out.println(dataBase.getCourseYearByCourseId(a));


        int b = dataBase.getCourseIdByGroupId(2);
        int c = dataBase.getCourseYearByCourseId(b);
        for (int i = 0; i < dataBase.getLessonsByEducationYear(c).size(); i++) {
            System.out.println(dataBase.getLessonsByEducationYear(c).get(i).getSubjectName());
        }
        System.out.println(dataBase.getTableByName("LessonTable").size());

        //System.out.println(dataBase.getLessonsScheduleForGroupId(2).get(0).getDayOfWeek());

        //System.out.println(dataBase.getLessonByLessonId(1));
        System.out.println(getScheduleForGroup(dataBase,1));

        //System.out.println(dataBase.getTeacherNameByTeacherId(dataBase.getLessonsByEducationYear(3).get(0).getTeacherId()));
        dataBase.saveAll();


    }
    private static String getScheduleForGroup(DataBase dataBase, int groupId){
        int educationYear = dataBase.getCourseYearByCourseId(dataBase.getCourseIdByGroupId(groupId));

        String fullString = "";
        for (int i = 0; i < dataBase.getLessonsScheduleForGroupId(groupId).size(); i++) {

            String tmpString = dataBase.getSubjectNameBySubjectId(dataBase.getLessonsScheduleForGroupId(groupId).get(i).getSubjectId()) + "," + dataBase.getTeacherNameByTeacherId(dataBase.getLessonsByEducationYear(educationYear).get(i).getTeacherId()) + ", "+dataBase.getLessonsScheduleForGroupId(groupId).get(i).getDayOfWeek() + ", " +dataBase.getLessonsScheduleForGroupId(groupId).get(i).getTime();
            fullString = fullString + "\n" + tmpString;
        }
        return fullString;
    }
}

package TableElements;


import Utils.CsvUtils;


public class Lesson{
    public enum TypeOfWeek{
        firstType,
        secondType
    }
    public enum LessonDay{
        Monday,
        Tuesday ,
        Wednesday,
        Thursday,
        Friday,
        Saturday,
        Sunday
    }
    public enum LessonTime {
        firstLesson("8:00"),
        secondLesson("9:45"),
        thirdLesson("11:30"),
        fourthLesson("13:25"),
        fifthLesson("15:10"),
        sixthLesson("16:55"),
        seventhLesson("18:40"),
        ;

        private final String time;

        LessonTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }
    }


    private int lessonId;
    private int groupId;
    private int subjectId;
    private String typeOfWeek;
    private String dayOfWeek;
    private String time;

    public Lesson(int lessonId,int groupId, int subjectId,String typeOfWeek, String dayOfWeek, String time) {
        this.lessonId = lessonId;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.typeOfWeek = typeOfWeek;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTypeOfWeek() {
        return typeOfWeek;
    }

    public void setTypeOfWeek(String typeOfWeek) {
        this.typeOfWeek = typeOfWeek;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}




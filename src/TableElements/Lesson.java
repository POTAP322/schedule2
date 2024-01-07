package TableElements;


import Utils.CsvUtils;


public class Lesson{
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


    private int groupId;
    private int subjectId;
    private String dayOfWeek;
    private String time;

    public Lesson(int groupId, int lessonId, String dayOfWeek, String time) {
        this.groupId = groupId;
        this.subjectId = lessonId;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
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




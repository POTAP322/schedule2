package TableElements;


import Utils.CsvUtils;

public class Lesson{

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

}




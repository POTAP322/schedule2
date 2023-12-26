package TableElements;



public class GroupLesson {

    private int groupId;
    private int lessonId;
    private String dayOfWeek;
    private String time;

    public GroupLesson(int groupId, int lessonId,String dayOfWeek, String time) {
        this.groupId = groupId;
        this.lessonId = lessonId;
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

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
}




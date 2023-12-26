package TableElements;

public class Lesson {
    private int lessonId;
    private String subjectName;
    private int teacherId;

    private int educationYear;



    public Lesson(int subjectId, String subjectName, int teacherId, int educationYear) {
        this.lessonId = subjectId;
        this.subjectName = subjectName;
        this.teacherId = teacherId;
        this.educationYear = educationYear;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setSubjectId(int subjectId) {
        this.lessonId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }
}

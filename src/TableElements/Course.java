package TableElements;

import Utils.CsvUtils;

public class Course implements TableData{
    private int courseId;
    private int educationYear;

    public Course(int courseId, int educationYear) {
        this.courseId = courseId;
        this.educationYear = educationYear;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getEducationYear() {
        return educationYear;
    }

    public void setEducationYear(int educationYear) {
        this.educationYear = educationYear;
    }

    @Override
    public String connectInLine() {
        return CsvUtils.connectInLine(courseId,educationYear);
    }
}


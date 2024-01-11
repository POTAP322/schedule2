package TableElements;

import Utils.CsvUtils;

public class Group{
    private int groupId;
    private String groupNumber;
    private int courseId;

    public Group(int groupId, String groupNumber, int courseId) {
        this.groupId = groupId;
        this.groupNumber = groupNumber;
        this.courseId = courseId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


}

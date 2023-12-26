package TableElements;

import Utils.CsvUtils;

public class Student implements TableData{
    private int studentId;
    private String name;
    private String surname;
    private int groupId;

    public Student(int studentId, String name, String surname, int groupId) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String connectInLine() {
        return CsvUtils.connectInLine(studentId,name,surname,groupId);
    }
}

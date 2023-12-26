package Tables;

import TableElements.Group;
import TableElements.Student;
import Utils.CsvUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class StudentTable implements Table, Iterable<Student> {
    private List<Student> students;
    public static final String name = "StudentTable";

    public StudentTable() {
        students = new ArrayList<>();
    }
    public Student getByStudentId(int studentId){
        for (Student student:students) {
            if(student.getStudentId()==studentId){
                return student;
            }
        }
        return null;
    }


    @Override
    public void save() {

        try {
            FileOutputStream fileOut = new FileOutputStream("src/Data/students.csv");
            for (Student student : students) {
                String data = CsvUtils.connectInLine(student.getStudentId(), student.getName(), student.getSurname(), student.getGroupId());
                fileOut.write(data.getBytes());
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Exception" + e);
        }
    }

    @Override
    public void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/students.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(",");

                int studentId = Integer.parseInt(strings[0]);
                String name = strings[1];
                String surname = strings[2];
                int groupId = Integer.parseInt(strings[3]);

                students.add(new Student(studentId,name,surname,groupId));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params) {
        int studentId = Integer.parseInt(params[0]);
        String name = params[1];
        String surname = params[2];
        int groupId = Integer.parseInt(params[3]);
        students.add(new Student(studentId,name,surname,groupId));

    }

    @Override
    public int size() {
        return students.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }


    @Override
    public void forEach(Consumer<? super Student> action) {
        Iterable.super.forEach(action);
    }
}


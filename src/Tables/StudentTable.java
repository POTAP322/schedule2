package Tables;

import TableElements.Group;
import TableElements.Student;
import Utils.CsvUtils;
import Utils.TableUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class StudentTable implements Table, Iterable<Student> {
    private List<Student> students;
    public static final String name = "StudentTable";

    public StudentTable() {
        students = new ArrayList<>();
    }

    public Student getByStudentId(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }


    @Override
    public void save() {

        try {
            FileOutputStream fileOut = new FileOutputStream("Data/students.csv");
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
            BufferedReader reader = new BufferedReader(new FileReader("Data/students.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] strings = line.split(",");

                int studentId = Integer.parseInt(strings[0]);
                String name = strings[1];
                String surname = strings[2];
                int groupId = Integer.parseInt(strings[3]);

                students.add(new Student(studentId, name, surname, groupId));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params) throws Exception {
        int newStudentId = TableUtils.generateNewId(students, student -> student.getStudentId());
        String name = params[0];
        String surname = params[1];
        int groupId = Integer.parseInt(params[2]);


        //проверка на существование студента
        File inputFile = new File("Data/students.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String lineToCheck = name + "," +surname + "," + groupId;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String treimmedLine = currentLine.trim();
            if (treimmedLine.contains(lineToCheck)) {
                throw new Exception("The student already exists");
            }
        }

        students.add(new Student(newStudentId, name, surname, groupId));

    }


    public void removeByFullName(String name, String surname){
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getName() + student.getSurname() == name + surname) {
                iterator.remove();
                break;
            }
        }

    }
    @Override
    public void removeById(int studentId){
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId() == studentId) {
                iterator.remove();
                break;
            }
        }
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


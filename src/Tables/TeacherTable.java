package Tables;

import TableElements.Course;
import TableElements.Student;
import TableElements.Teacher;
import Utils.CsvUtils;
import Utils.TableUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class TeacherTable implements Table,Iterable<Teacher>{
    private List<Teacher> teachers;
    public static final String name = "TeacherTable";

    public TeacherTable() {
        teachers = new ArrayList<>();
    }


    @Override
    public void save(){

        try {
            FileOutputStream fileOut = new FileOutputStream("Data/teachers.csv");
            for(Teacher teacher:teachers ) {
                String data = CsvUtils.connectInLine(teacher.getTeacherId(),teacher.getName(),teacher.getSurname());
                fileOut.write(data.getBytes());
            }
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Exception"+e);
        }
    }

    @Override
    public void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Data/teachers.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int teacherId = Integer.parseInt(strings[0]);
                String name = strings[1];
                String surname = strings[2];


                teachers.add(new Teacher(teacherId,name,surname));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params) throws Exception {
        int newTeacherId = TableUtils.generateNewId(teachers, teacher -> teacher.getTeacherId());
        String name = params[0];
        String surname = params[1];

        //проверка на существование преподавателя
        File inputFile = new File("Data/teachers.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String lineToCheck = name + "," +surname;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String treimmedLine = currentLine.trim();
            if (treimmedLine.contains(lineToCheck)) {
                throw new Exception("The teacher already exists");
            }
        }

        teachers.add(new Teacher(newTeacherId,name,surname));
    }


    public void remove(String... params) {

    }

    @Override
    public void removeById(int id) {
        Iterator<Teacher> iterator = teachers.iterator();
        while (iterator.hasNext()) {
            Teacher teacher = iterator.next();
            if (teacher.getTeacherId() == id) {
                iterator.remove();
                break;
            }
        }
    }


    @Override
    public int size() {
        return teachers.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Iterator<Teacher> iterator() {
        return teachers.iterator();
    }

    @Override
    public void forEach(Consumer<? super Teacher> action) {
        Iterable.super.forEach(action);
    }
}

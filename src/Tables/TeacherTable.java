package Tables;

import TableElements.Course;
import TableElements.Teacher;
import Utils.CsvUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
    public void add(String... params) {
        int teacherId = Integer.parseInt(params[0]);
        String name = params[1];
        String surname = params[2];
        teachers.add(new Teacher(teacherId,name,surname));
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

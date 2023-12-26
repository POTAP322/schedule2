package Tables;
import TableElements.Course;
import TableElements.Group;
import Utils.CsvUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class CourseTable implements Table, Iterable<Course>{
    private List<Course> courses;
    public static final String name = "CourseTable";

    public CourseTable() {
        courses = new ArrayList<>();
    }


    @Override
    public void save(){

        try {
            FileOutputStream fileOut = new FileOutputStream("Data/courses.csv");
            for(Course course:courses ) {
                String data = CsvUtils.connectInLine(course.getCourseId(),course.getEducationYear());
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
            BufferedReader reader = new BufferedReader(new FileReader("Data/courses.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int courseId = Integer.parseInt(strings[0]);
                int educationYear = Integer.parseInt(strings[1]);

                courses.add(new Course(courseId,educationYear));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params){
        int courseId = Integer.parseInt(params[0]);
        int educationYear = Integer.parseInt(params[1]);

        courses.add(new Course(courseId,educationYear));

    }

    @Override
    public void remove() {

    }

    @Override
    public int size() {
        return courses.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Iterator<Course> iterator() {
        return courses.iterator();
    }

    @Override
    public void forEach(Consumer<? super Course> action) {
        Iterable.super.forEach(action);
    }
}

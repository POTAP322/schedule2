package Tables;
import TableElements.Course;
import TableElements.Group;
import TableElements.Lesson;
import TableElements.Student;
import Utils.CsvUtils;
import Utils.TableUtils;

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
    public void add(String... params) throws  Exception{
        int newCourseId = TableUtils.generateNewId(courses, Course::getCourseId);
        int educationYear = Integer.parseInt(params[0]);

        File inputFile = new File("Data/courses.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String lineToCheck = educationYear+"";
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String treimmedLine = currentLine.trim();
            if (treimmedLine.contains(lineToCheck)) {
                throw new Exception("The course already exists");
            }
        }

        courses.add(new Course(newCourseId,educationYear));
    }


    public void remove(String... params) {

    }

    @Override
    public void removeById(int id) {
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getCourseId() == id) {
                iterator.remove();
                break;
            }
        }

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
    public void addNew(int i, int i1, Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime firstLesson) {

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

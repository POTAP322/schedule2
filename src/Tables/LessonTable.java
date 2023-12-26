package Tables;

import TableElements.Lesson;
import Utils.CsvUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class LessonTable implements Table,Iterable<Lesson>{
    private List<Lesson> lessons;
    public static final String name = "LessonTable";

    public LessonTable() {
        lessons= new ArrayList<>();
    }


    @Override
    public void save(){

        try {
            FileOutputStream fileOut = new FileOutputStream("src/Data/lessons.csv");
            for(Lesson lesson:lessons ) {
                String data = CsvUtils.connectInLine(lesson.getLessonId(),lesson.getSubjectName(),lesson.getTeacherId(),lesson.getEducationYear());
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
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/lessons.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int subjectId = Integer.parseInt(strings[0]);
                String subjectName = strings[1];
                int teacherId = Integer.parseInt(strings[2]);
                int educationYear = Integer.parseInt(strings[3]);

                lessons.add(new Lesson(subjectId,subjectName,teacherId,educationYear));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params) {
        int subjectId = Integer.parseInt(params[0]);
        String subjectName = params[1];
        int teacherId = Integer.parseInt(params[2]);
        int educationYear = Integer.parseInt(params[3]);
        lessons.add(new Lesson(subjectId,subjectName,teacherId,educationYear));

    }

    @Override
    public int size() {
        return lessons.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Iterator iterator() {
        return lessons.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }
}

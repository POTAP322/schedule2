package Tables;

import TableElements.Group;
import TableElements.Lesson;
import TableElements.Student;
import TableElements.Subject;
import Utils.CsvUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class LessonTable implements Table,Iterable<Lesson> {

    private List<Lesson> lessons;
    public static final String name = "LessonTable";

    public LessonTable() {
        lessons = new ArrayList<>();
    }


    @Override
    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Data/lessons.csv");
            for(Lesson lesson : lessons) {
                String data = CsvUtils.connectInLine(lesson.getGroupId(), lesson.getSubjectId(),lesson.getTypeOfWeek(), lesson.getDayOfWeek(), lesson.getTime());
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
            BufferedReader reader = new BufferedReader(new FileReader("Data/lessons.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int groupId = Integer.parseInt(strings[0]);
                int lessonId = Integer.parseInt(strings[1]);
                String typeOfWeek = strings[2];
                String dayOfWeek = strings[3];
                String time = strings[4];

                lessons.add(new Lesson(groupId,lessonId,typeOfWeek,dayOfWeek,time));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void add(String... params) {
        int groupId = Integer.parseInt(params[0]);
        int subjectId = Integer.parseInt(params[1]);
        String typeOfWeek = params[2];
        String dayOfWeek = params[3];
        String time = params[4];
        lessons.add(new Lesson(groupId,subjectId,typeOfWeek,dayOfWeek,time));
    }


    public void addNewByGroupAndCourse(Group group, Subject subject,Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime time){
        int groupId = group.getGroupId();
        int subjectId = subject.getSubjectId();
        String weekType = String.valueOf(typeOfWeek);
        String dayOfWeek = String.valueOf(day);
        String lessonTime = time.getTime();
        lessons.add(new Lesson(groupId,subjectId,weekType,dayOfWeek,lessonTime));
    }


    public void addNew(int groupId, int subjectId,Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime time) {
        String weekType = String.valueOf(typeOfWeek);
        String dayOfWeek = String.valueOf(day);
        String lessonTime = time.getTime();
        lessons.add(new Lesson(groupId,subjectId,weekType,dayOfWeek,lessonTime));
    }


    public void remove(String... params) {

    }

    @Override
    public void removeById(int subjectId) {
        Iterator<Lesson> iterator = lessons.iterator();
        while (iterator.hasNext()) {
            Lesson lesson = iterator.next();
            if (lesson.getSubjectId() == subjectId ) {
                iterator.remove();
                break;
            }
        }

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
    public Iterator<Lesson> iterator() {
        return lessons.iterator();
    }

    @Override
    public void forEach(Consumer<? super Lesson> action) {
        Iterable.super.forEach(action);
    }
}

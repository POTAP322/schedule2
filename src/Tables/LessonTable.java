package Tables;

import TableElements.Group;
import TableElements.Lesson;
import TableElements.Student;
import TableElements.Subject;
import Utils.CsvUtils;
import Utils.TableUtils;

import java.io.*;
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
                String data = CsvUtils.connectInLine(lesson.getLessonId(),lesson.getGroupId(), lesson.getSubjectId(),lesson.getTypeOfWeek(), lesson.getDayOfWeek(), lesson.getTime());
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

                int lessonId = Integer.parseInt(strings[0]);
                int groupId = Integer.parseInt(strings[1]);
                int subjectId = Integer.parseInt(strings[2]);
                String typeOfWeek = strings[3];
                String dayOfWeek = strings[4];
                String time = strings[5];

                lessons.add(new Lesson(lessonId,groupId,subjectId,typeOfWeek,dayOfWeek,time));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void add(String... params) {
        int newLessonId = TableUtils.generateNewId(lessons, lesson -> lesson.getLessonId());
        int groupId = Integer.parseInt(params[1]);
        int subjectId = Integer.parseInt(params[2]);
        String typeOfWeek = params[3];
        String dayOfWeek = params[4];
        String time = params[5];
        lessons.add(new Lesson(newLessonId,groupId,subjectId,typeOfWeek,dayOfWeek,time));
    }


    public void addNewByGroupAndCourse(Group group, Subject subject,Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime time){
        int newLessonId = TableUtils.generateNewId(lessons, lesson -> lesson.getLessonId());
        int groupId = group.getGroupId();
        int subjectId = subject.getSubjectId();
        String weekType = String.valueOf(typeOfWeek);
        String dayOfWeek = String.valueOf(day);
        String lessonTime = time.getTime();
        lessons.add(new Lesson(newLessonId,groupId,subjectId,weekType,dayOfWeek,lessonTime));
    }


    public void addNew(int groupId, int subjectId,Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime time) throws Exception {
        int newLessonId = TableUtils.generateNewId(lessons, lesson -> lesson.getLessonId());
        String weekType = String.valueOf(typeOfWeek);
        String dayOfWeek = String.valueOf(day);
        String lessonTime = time.getTime();

        //проверка на то, что в данное время у группы есть другой урок
        File inputFile = new File("Data/lessons.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String[] parts = currentLine.split(",");
            if (parts[1].equals(String.valueOf(groupId)) && parts[3].equals(weekType) && parts[4].equals(dayOfWeek) && parts[5].equals(lessonTime)) {
                throw new Exception("В это время у группы есть другой урок");
            }
        }

        lessons.add(new Lesson(newLessonId,groupId,subjectId,weekType,dayOfWeek,lessonTime));
    }


    public void remove(String... params) {

    }

    @Override
    public void removeById(int lessonId) {
        Iterator<Lesson> iterator = lessons.iterator();
        while (iterator.hasNext()) {
            Lesson lesson = iterator.next();
            if (lesson.getLessonId() == lessonId ) {
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

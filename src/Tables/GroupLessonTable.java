package Tables;

import TableElements.Course;
import TableElements.GroupLesson;
import Utils.CsvUtils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class GroupLessonTable implements Table,Iterable<GroupLesson> {

    private List<GroupLesson> groupLessons;
    public static final String name = "GroupLessonTable";

    public GroupLessonTable() {
        groupLessons = new ArrayList<>();
    }


    @Override
    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/Data/groupLesson.csv");
            for(GroupLesson groupLesson:groupLessons ) {
                String data = CsvUtils.connectInLine(groupLesson.getGroupId(),groupLesson.getLessonId(),groupLesson.getDayOfWeek(),groupLesson.getTime());
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
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/groupLesson.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int groupId = Integer.parseInt(strings[0]);
                int lessonId = Integer.parseInt(strings[1]);
                String dayOfWeek = strings[2];
                String time = strings[3];

                groupLessons.add(new GroupLesson(groupId,lessonId,dayOfWeek,time));
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
        int lessonId = Integer.parseInt(params[1]);
        String dayOfWeek = params[2];
        String time = params[3];
        groupLessons.add(new GroupLesson(groupId,lessonId,dayOfWeek,time));

    }

    @Override
    public int size() {
        return groupLessons.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Iterator<GroupLesson> iterator() {
        return groupLessons.iterator();
    }

    @Override
    public void forEach(Consumer<? super GroupLesson> action) {
        Iterable.super.forEach(action);
    }
}

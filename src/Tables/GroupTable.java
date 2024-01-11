package Tables;

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

public class GroupTable implements Table,Iterable<Group>{

    private List<Group> groups;
    public static final String name = "GroupTable";

    public GroupTable() {
        groups = new ArrayList<>();
    }


    @Override
    public void save(){

        try {
            FileOutputStream fileOut = new FileOutputStream("Data/groups.csv");
            for(Group group:groups ) {
               String data = CsvUtils.connectInLine(group.getGroupId(),group.getGroupNumber(),group.getCourseId());
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
            BufferedReader reader = new BufferedReader(new FileReader("Data/groups.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int groupId = Integer.parseInt(strings[0]);
                String groupNumber = strings[1];
                int courseId = Integer.parseInt(strings[2]);

                groups.add(new Group(groupId,groupNumber,courseId));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params) throws Exception {
        int newGroupId = TableUtils.generateNewId(groups, group -> group.getGroupId());
        String groupNumber = params[0];
        int courseId = Integer.parseInt(params[1]);

        File inputFile = new File("Data/groups.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String lineToCheck = groupNumber + "," +courseId;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String treimmedLine = currentLine.trim();
            if (treimmedLine.contains(lineToCheck)) {
                throw new Exception("The group already exists");
            }
        }

        groups.add(new Group(newGroupId,groupNumber,courseId));

    }


    public void remove(String... params ) {


    }

    @Override
    public void removeById(int id) {
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()) {
            Group group = iterator.next();
            if (group.getGroupId() == id) {
                iterator.remove();
                break;
            }
        }

    }

    @Override
    public int size() {
        return groups.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public void addNew(int i, int i1, Lesson.TypeOfWeek typeOfWeek, Lesson.LessonDay day, Lesson.LessonTime firstLesson) {

    }


    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }

    @Override
    public void forEach(Consumer<? super Group> action) {
        Iterable.super.forEach(action);
    }
}

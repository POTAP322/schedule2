package Tables;

import TableElements.Group;
import Utils.CsvUtils;

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
            FileOutputStream fileOut = new FileOutputStream("src/Data/groups.csv");
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
            BufferedReader reader = new BufferedReader(new FileReader("src/Data/groups.csv"));
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
    public void add(String... params) {
        int groupId = Integer.parseInt(params[0]);
        String groupNumber = params[1];
        int courseId = Integer.parseInt(params[2]);
        groups.add(new Group(groupId,groupNumber,courseId));

    }

    @Override
    public String getTableName() {
        return name;
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

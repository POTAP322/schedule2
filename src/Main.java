import TableElements.Group;
import Tables.GroupTable;

public class Main {
    public static void main(String[] args) {
       DataBase dataBase = new DataBase();

        dataBase.loadAll();
        //махинации проводим тута

        for (int i = 0; i < dataBase.getStudentsByGroupId(1).getLast().getStudentId(); i++) {
            System.out.println(dataBase.getStudentsByGroupId(1).get(i).getName());
        }
        System.out.println(dataBase.getCourseIdByGroupId(2));
        int a = dataBase.getCourseIdByGroupId(2);
        System.out.println(dataBase.getCourseYearByCourseId(a));
        dataBase.saveAll();


    }
}

import TableElements.*;

import java.time.DayOfWeek;
import java.util.Scanner;

public class Schedule {
    public static void main(String[] args) throws Exception {
        scheduleProgram();

    }


    //todo возможно методы для добавления новых учителей (или удаления) и добавления новых предметов( хотя они и так есть )
    //private static void addTeacher
    private static void scheduleProgram() throws Exception {
        DataBase dataBase = new DataBase();
        dataBase.loadAll();
        Scanner scanner = new Scanner(System.in);

        printGroups(dataBase);
        System.out.print("Выберите группу(по group id): ");
        int groupIndex = scanner.nextInt();
        Group group = dataBase.getGroupByGroupId(groupIndex);

        System.out.println("Что вы хотите сделать с группой " + group.getGroupNumber() + " на " + group.getCourseId() + " курсе" + "?");
        System.out.println("1. Вывести расписание");
        System.out.println("2. Вывести учеников");
        System.out.println("3. Изменить расписание или состав группы");
        int action = scanner.nextInt();

        switch (action) {
            case 1:
                System.out.println(getScheduleForGroup(dataBase, groupIndex));
                System.out.println("-----------------------------------");
                scheduleProgram();

            case 2:
                for (int i = 0; i < dataBase.getStudentsByGroupId(groupIndex).size(); i++) {
                    System.out.println("id:" + dataBase.getStudentsByGroupId(groupIndex).get(i).getStudentId() + " " + dataBase.getStudentsByGroupId(groupIndex).get(i).getName() + " " + dataBase.getStudentsByGroupId(groupIndex).get(i).getSurname());
                }
                System.out.println("-----------------------------------");
                scheduleProgram();

            case 3:
                System.out.println("Что вы хотите изменить?");
                //System.out.println("0. Выход");
                System.out.println("1. Ученики");
                System.out.println("2. Занятия");
                //System.out.println("3. Учителя");
                int action2 = scanner.nextInt();
                do {
                    switch (action2) {
                        case 1:
                            // Действия при выборе ученика
                            System.out.println("Какое действие произвести: ");
                            System.out.println("0. Выход");
                            System.out.println("1. Удалить ученика");
                            System.out.println("2. Добавить ученика");
                            int action3 = scanner.nextInt();
                            switch (action3) {
                                case 0:
                                    action2 = 0;
                                    break;
                                case 1:
                                    for (int i = 0; i < dataBase.getStudentsByGroupId(groupIndex).size(); i++) {
                                        System.out.println("id:" + dataBase.getStudentsByGroupId(groupIndex).get(i).getStudentId() + " " + dataBase.getStudentsByGroupId(groupIndex).get(i).getName() + " " + dataBase.getStudentsByGroupId(groupIndex).get(i).getSurname());
                                    }
                                    System.out.println("Какого ученика удалить(id): ");
                                    int id = scanner.nextInt();
                                    dataBase.getTableByName("StudentTable").removeById(id);
                                    dataBase.saveAll();
                                    break;

                                case 2:
                                    System.out.println("Введите имя:");
                                    String name = scanner.next();
                                    System.out.println("Введите фамилию:");
                                    String surname = scanner.next();
                                    dataBase.getTableByName("StudentTable").add(name, surname, String.valueOf(groupIndex));
                                    break;

                            }
                            break;
                        case 2:
                            System.out.println("Какое действие произвести: ");
                            System.out.println("0. Выход");
                            System.out.println("1. Удалить занятие");
                            System.out.println("2. Добавить занятие");
                            int action4 = scanner.nextInt();
                            switch (action4) {
                                case 0:
                                    action2 = 0;
                                    break;
                                case 1:
                                    for (int i = 0; i < dataBase.getLessonsByGroupId(groupIndex).size(); i++) {
                                        System.out.println("id:" + dataBase.getLessonsByGroupId(groupIndex).get(i).getLessonId() + " " + "Subject: " + dataBase.getSubjectNameBySubjectId(dataBase.getLessonsByGroupId(groupIndex).get(i).getSubjectId()) + " " + dataBase.getLessonsByGroupId(groupIndex).get(i).getDayOfWeek() + " " + dataBase.getLessonsByGroupId(groupIndex).get(i).getTime());
                                    }
                                    System.out.println("Какое занятие удалить(id): ");
                                    int id = scanner.nextInt();
                                    dataBase.getTableByName("LessonTable").removeById(id);
                                    dataBase.saveAll();
                                    break;

                                case 2:
                                    for (int i = 0; i < dataBase.getSubjects().size(); i++) {
                                        System.out.println("id:" + dataBase.getSubjects().get(i).getSubjectId() + " Название: " + dataBase.getSubjects().get(i).getSubjectName());
                                    }
                                    System.out.println("Введите id предмета:");
                                    int subjId = scanner.nextInt();

                                    for (Lesson.LessonDay day : Lesson.LessonDay.values()) {
                                        System.out.println(day);
                                    }
                                    System.out.println("Введите день недели:");
                                    String input = scanner.next();
                                    Lesson.LessonDay selectedDay = Lesson.LessonDay.valueOf(input);

                                    for (Lesson.TypeOfWeek typeOfWeek : Lesson.TypeOfWeek.values()) {
                                        System.out.println(typeOfWeek);
                                    }
                                    System.out.println("Выберите тип недели(числитель или знаменатель): ");
                                    String input2 = scanner.next();
                                    Lesson.TypeOfWeek typeOfWeek = Lesson.TypeOfWeek.valueOf(input2);

                                    for (Lesson.LessonTime lessonTime : Lesson.LessonTime.values()) {
                                        System.out.println(lessonTime + " " + lessonTime.getTime());
                                    }
                                    System.out.println("Введите номер урока: ");
                                    String input3 = scanner.next();
                                    Lesson.LessonTime lessonTime = Lesson.LessonTime.valueOf(input3);


                                    dataBase.getTableByName("LessonTable").addNew(groupIndex, subjId, typeOfWeek, selectedDay, lessonTime);
                                    dataBase.saveAll();
                                    break;

                            }
                            break;
                        case 3:


                            break;
                        default:
                            System.out.println("Некорректный выбор.");
                            break;
                    }
                } while (action2 != 0);
                break;

            default:
                System.out.println("Некорректный выбор.");
                break;
        }
        dataBase.saveAll();

    }

    private static void printGroups(DataBase dataBase) {
        int size = dataBase.getTableByName("GroupTable").size();
        for (int i = 0; i < size; i++) {
            System.out.println("Group id: " + dataBase.getGroups().get(i).getGroupId() + " ; " + "Group number: " + dataBase.getGroups().get(i).getGroupNumber() + " ; " + "Group year: " + dataBase.getGroups().get(i).getCourseId());
        }

    }

    private static String getScheduleForGroup(DataBase dataBase, int groupId) {
        int educationYear = dataBase.getCourseYearByCourseId(dataBase.getCourseIdByGroupId(groupId));

        String fullString = "";
        for (int i = 0; i < dataBase.getLessonsScheduleForGroupId(groupId).size(); i++) {

            String tmpString = dataBase.getSubjectNameBySubjectId(dataBase.getLessonsScheduleForGroupId(groupId).get(i).getSubjectId()) + "," + dataBase.getTeacherNameByTeacherId(dataBase.getLessonsByEducationYear(educationYear).get(i).getTeacherId()) + ", " + dataBase.getLessonsScheduleForGroupId(groupId).get(i).getDayOfWeek() + ", " + dataBase.getLessonsScheduleForGroupId(groupId).get(i).getTime();
            fullString = fullString + "\n" + tmpString;
        }
        return fullString;
    }


}

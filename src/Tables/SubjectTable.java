package Tables;

import TableElements.Student;
import TableElements.Subject;
import Utils.CsvUtils;
import Utils.TableUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SubjectTable implements Table,Iterable<Subject>{
    private List<Subject> subjects;
    public static final String name = "SubjectTable";

    public SubjectTable() {
        subjects = new ArrayList<>();
    }


    @Override
    public void save(){

        try {
            FileOutputStream fileOut = new FileOutputStream("Data/subject.csv");
            for(Subject subject : subjects) {
                String data = CsvUtils.connectInLine(subject.getSubjectId(), subject.getSubjectName(), subject.getTeacherId(), subject.getEducationYear());
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
            BufferedReader reader = new BufferedReader(new FileReader("Data/subject.csv"));
            String line = reader.readLine();
            while (line != null) {
                String [] strings = line.split(",");

                int subjectId = Integer.parseInt(strings[0]);
                String subjectName = strings[1];
                int teacherId = Integer.parseInt(strings[2]);
                int educationYear = Integer.parseInt(strings[3]);

                subjects.add(new Subject(subjectId,subjectName,teacherId,educationYear));
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void add(String... params) throws Exception{
        int newSubjectId = TableUtils.generateNewId(subjects, subject -> subject.getSubjectId());
        String subjectName = params[0];
        int teacherId = Integer.parseInt(params[1]);
        int educationYear = Integer.parseInt(params[2]);

        //проверка на существование предмета
        File inputFile = new File("Data/subject.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String lineToCheck = subjectName + "," +teacherId + "," + educationYear;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String treimmedLine = currentLine.trim();
            if (treimmedLine.contains(lineToCheck)) {
                throw new Exception("The subject already exists");
            }
        }

        subjects.add(new Subject(newSubjectId,subjectName,teacherId,educationYear));

    }



    public void remove(String... params) {

    }

    @Override
    public void removeById(int id) {
        Iterator<Subject> iterator = subjects.iterator();
        while (iterator.hasNext()) {
            Subject subject = iterator.next();
            if (subject.getSubjectId() == id) {
                iterator.remove();
                break;
            }
        }

    }

    @Override
    public int size() {
        return subjects.size();
    }

    @Override
    public String getTableName() {
        return name;
    }

    @Override
    public Iterator iterator() {
        return subjects.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }
}

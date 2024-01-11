package Utils;

public class CsvUtils {

    //соединияем всякие разные данные в строчку
    public static String connectInLine(Object... objects){ //массив обжектов определенный длинны
        StringBuilder dataBuilder = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            dataBuilder.append(objects[i]);
            if(i+1 < objects.length){
                dataBuilder.append(",");
            }

        }
        dataBuilder.append("\n");
        return dataBuilder.toString();
    }

}

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//        *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
public class Buble_sort {
    static Logger logger;
    public static void main(String[] args) {
        int [] array = new int[]{23, 25, 34, 12, 45, 56, 76, 32, 13, 9};

        System.out.println(array);
        createLogger();
        bubleSort(array);
        closeLogger();
    }

    private static void closeLogger() {
        for (Handler handler: logger.getHandlers()){
            handler.close();
        }
    }

    private static void createLogger() {
        logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("/Users/rostislavnosovskij/Desktop/Lesson2Homework/log2.txt", true);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleFormatter formatter = new SimpleFormatter();
        if (fileHandler != null){
            fileHandler.setFormatter(formatter);
        }
    }
    static void bubleSort (int[] array){
           for (int i = 0; i< array.length-1; i++){
            for (int j = 0; j< array.length-i-1; j++){
                if (array[j+1] < array[j]) {
                    int s = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = s;
                    for (int k = 0; k< array.length; k++){
                        String n = Integer.toString(array[i]);
                        logger.info(Arrays.toString(array));}
                }
            }
        }
    }

}

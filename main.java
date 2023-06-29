// *Получить исходную json строку из файла, используя FileReader или Scanner
//        Дана json строка вида:
//        String json = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
//        "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
//        "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
//
//        Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
//        Студент Иванов получил 5 по предмету Математика.
//        Студент Петрова получил 4 по предмету Информатика.
//        Студент Краснов получил 5 по предмету Физика.
//
//        Используйте StringBuilder для подготовки ответа. Далее создайте метод, который запишет
//        результат работы в файл. Обработайте исключения и запишите ошибки в лог файл с помощью Logger.
//
//        *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class main {
    static Logger logger;

    public static void main(String[] args) {
        String file_path = "/Users/rostislavnosovskij/Desktop/Lesson2Homework/text.txt";
        String file_at_string  = readInFile(file_path);
        String names = SplitString(file_at_string, "Студент");
        String rating = SplitString(file_at_string, "получил");
        String subject = SplitString(file_at_string, "предмету");
        createLogger();
       String json = json(names, rating, subject);
        writeToFile(json ,file_path);
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
            fileHandler = new FileHandler("/Users/rostislavnosovskij/Desktop/Lesson2Homework/log.txt", true);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleFormatter formatter = new SimpleFormatter();
        if (fileHandler != null){
            fileHandler.setFormatter(formatter);
        }
    }

    static String json (String names, String rating, String subject){
        StringBuilder res = new StringBuilder();
        String [] names_array = names.split(" ");
        String [] rating_array = rating.split(" ");
        String [] subject_array = subject.split(" ");
        for (int i = 0; i< names_array.length; i++){
            res.append("\"[{\\\"фамилия\\\":\\\"");
            res.append(names_array[i]);
            res.append("\\\",\\\"оценка\\\":\\\"");
            res.append(rating_array[i]);
            res.append("\\\",\\\"предмет\\\":\\\"");
            res.append(subject_array[i]);
            res.append("\\\"},\"");
            if (i==names_array.length-1){
                res.append(";");
            }else{
                res.append(" +");
                res.append("\n");
            }

        }
        return res.toString();
    }

    private static String SplitString(String string, String word) {
        StringBuilder res = new StringBuilder();
        String [] words = string.split(" ");
        for (int i = 0; i< words.length; i++){
            if (words[i].equals(word)){
                res.append(words[i+1]);
                res.append(" ");
            }
        }
        return res.toString();

    }


    static String readInFile(String filePath) {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()){
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append("\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

    static void writeToFile(String s1, String filePath) {

        try (FileWriter writer = new FileWriter(filePath, true)){
            writer.write(s1);
            writer.write("\n");
            writer.flush();
            logger.info("Файл успешно записан");
        } catch (Exception e){
            e.printStackTrace();
            logger.warning("Произошла ошибка");
        }
    }

}



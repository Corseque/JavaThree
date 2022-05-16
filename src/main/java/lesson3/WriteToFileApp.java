package lesson3;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WriteToFileApp {

    private static final File file = new File("src/main/java/lesson3", "file.txt");

    public static File getFile() {
        return file;
    }

    public static void main(String[] args) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fileWriter = new FileWriter(file/*, true*/)) {
            for (int i = 0; i < 100_000_000; i++) {
                fileWriter.write("" + (i + 1) + "\n");
                System.out.println("Записали: " + "" + (i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

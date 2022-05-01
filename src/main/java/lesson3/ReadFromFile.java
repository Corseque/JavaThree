package lesson3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

    private final List<String> list = new ArrayList<>();


    public ReadFromFile() {
        startRead();
    }

    public void startRead() {
        //System.out.println(WriteToFileApp.getFile().canRead());
        try (BufferedReader br = new BufferedReader(new FileReader(WriteToFileApp.getFile()))) {
            for (String line; (line = br.readLine()) != null; ) {
                list.add(line);
                if (Integer.parseInt(line) % 10000 == 0 && Integer.parseInt(line) != 0) {
                    for (int i = list.size() - 11; i < list.size(); i++) {
                        System.out.println(list.get(i));
                    }
                }
            }
            // line is not visible here.
        } catch (IOException e) {
            e.printStackTrace();
        }
        //}).start();
    }

}
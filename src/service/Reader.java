package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Reader {

    private static final String path = "resources/task.txt";

    public static String sysCount() {
        int max = Integer.MIN_VALUE;
        String value;

        if (Files.exists(Paths.get(path))) {
            try (FileReader fileReader = new FileReader(path)){
                Scanner scanner = new Scanner(fileReader);
                value = scanner.next();
                try {
                    for (int i = 0; i < value.length(); i++) {
                        if (Integer.parseInt(value.split("")[i]) > max) {
                            max = Integer.parseInt(value.split("")[i]) + 1;
                        }
                    }
                } catch (NumberFormatException e) {
                    return value + "=invalid";
                }
                return String.valueOf( Integer.parseInt(value, max));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return "File doesnt exist";
    }

}

package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileScanner {

    private static final String path = "resources/test.txt";

    public static int readSum() {
        int sum = 0;
        if (Files.exists(Paths.get(path))) {
            try (FileReader reader = new FileReader(path)) {
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNext()) {
                    try {
                        String temp = scanner.next();
                        for (int i = 0; i < temp.length(); i++) {
                            sum += Integer.parseInt(temp.split("")[i]);
                        }
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return sum;
    }

}

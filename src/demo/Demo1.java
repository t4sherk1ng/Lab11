package demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Demo1 {
    static String path = "resources/example.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String lastInput = "";
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                writeToFile(lastInput);
                break;
            }
            lastInput = input;
        }

        scanner.close();
    }

    static void writeToFile(String text) {
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(text);
            writer.write(System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
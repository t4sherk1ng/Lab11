package demo.demo2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static String path = "resources/example.txt";
    static Person[] persons = {
            new Person("Федоров","Федор","Федорович",1995, "man"),
            new Person("Сидороров","Сидор","Сидорорович",1971, "man"),
            new Person("Петров","Петр","Петрович",1985, "man"),
            new Person("Иванов","Иван","Иванович",1971, "man")
    };
    public static boolean save(Person[] persons){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(persons);
            out.flush();
            return true;
        }catch(IOException ex){
            System.out.println("Exception: " + ex.getClass()+ " in " + ex.getMessage());
        }
        return false;
    }
    public static void reload() {
        if (Files.exists(Paths.get(path))) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                Person[] persons = (Person[]) in.readObject();
                for (Person man : persons) {
                    System.out.println(man);
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Считанные данные оказались иного формата: " + ex.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("There is no file at such path: " + path);
            } catch (IOException e) {
                System.out.println("Something wrong: " + e.getMessage());
            }
        } else {
            System.out.println("There is no file at such path: " + path);
        }
    }

    static String temp[];
    public static void main(String[] args) {
        for (Person man: readPersons()){
            System.out.println(man);
        }
    }
    static Person[] readPersons(){
        Person[] people = null;
        int count = 0;
        if (Files.exists(Paths.get(path))){
            try {
                count = (int) Files.lines(Paths.get(path)).count();
                people = new Person[count];
            } catch (IOException e) {
                System.out.println("Exception" + e.getMessage());
            }
        }
        try (FileReader reader = new FileReader(path)){
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()){
                temp = scanner.nextLine().split(" ");
                people[--count] = new Person(temp[0], temp[1], temp[2], Integer.parseInt(temp[3]), temp[4]);
            }
        }catch (FileNotFoundException e){
            System.out.println("Wrong path " + e.getMessage());
        }catch (IOException e){
            System.out.println("Exception" + e.getMessage());
        }
        return people;
    }
}
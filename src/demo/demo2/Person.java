package demo.demo2;

import java.io.*;

public class Person implements Serializable {
    private String surname;
    private String name;
    private String patronymic;
    private transient int year;
    private Gender gender;

    public Person(String surname, String name, String patronymic, int year, String gender) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.year = year;
        this.gender = new Gender(gender);
    }


    @Override
    public String toString() {
        String prefix = (gender.getGender().equalsIgnoreCase(Gender.MALE)) ? "Уважаемый" : "Уважаемая";
        return String.format("%s %s %s!", prefix, name, patronymic);
    }

    class Gender implements Serializable {
        private static final String MALE = "man";
        private static final String FEMALE = "woman";
        private String gender = MALE;

        public Gender(String gender) {
            if (gender != null
                    && gender instanceof String
                    && (gender.equalsIgnoreCase(MALE)
                    || gender.equalsIgnoreCase(FEMALE))) {
                this.gender = gender;
            }
        }

        public String getGender() {
            return gender;
        }
    }
}

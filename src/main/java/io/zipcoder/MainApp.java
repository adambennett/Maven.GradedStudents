package io.zipcoder;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class MainApp {



    public static void main(String[] args) {
        Classroom classroom = new Classroom();

        ArrayList<Student> randStuds = Student.createRandomStudents(100);
        for (Student s : randStuds) {
            for (int i = 0; i < ThreadLocalRandom.current().nextInt(2, 4); i++) {
                s.addExamScore(ThreadLocalRandom.current().nextDouble(1, 101));
            }
        }
        classroom.addAllStudents(randStuds);
        Map<Character, ArrayList<Student>> mapp = classroom.getLetterGrade();
        for (Map.Entry<Character, ArrayList<Student>> i : mapp.entrySet()) {
            String allStudents = "";
            for (Student s : i.getValue()) {
                allStudents += s.toString() + "\n";
            }
            Logger.getGlobal().info("\n\nSCORE: " + i.getKey() + "\n\nSTUDENTS: \n\n" + allStudents);
        }

    }

}

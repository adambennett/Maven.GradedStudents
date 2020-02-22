package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class ClassroomTest {

    @Test
    public void getStudents() {
        Classroom classroom = new Classroom(5);
        ArrayList<Student> expected = classroom.getStudents();
        ArrayList<Student> actual = new ArrayList<>();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAverageExamScore() {
        Classroom classroom = new Classroom(5);
        Student stud = new Student("Adam", "Bennett", new Double[] { 100.0 });
        Student studB = new Student("John", "Bennett", new Double[] { 50.0 });

        classroom.addStudent(stud);
        classroom.addStudent(studB);

        Double expected = 75.0;
        Double actual = classroom.getAverageExamScore();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addStudent() {
        Classroom classroom = new Classroom(5);
        Student stud = new Student("Adam", "Bennett", new Double[] { 100.0 });
        Student studB = new Student("John", "Bennett", new Double[] { 50.0 });

        classroom.addStudent(stud);
        classroom.addStudent(studB);
        Assert.assertTrue(classroom.getStudents().size() == 2);
    }

    @Test
    public void addAllStudents() {
        Classroom classroom = new Classroom();
        ArrayList<Student> randStuds = Student.createRandomStudents(100);
        classroom.addAllStudents(randStuds);
        Assert.assertTrue(classroom.getStudents().size() == 100);
    }

    @Test
    public void removeStudent() {
        Classroom classroom = new Classroom(5);
        Student stud = new Student("Adam", "Bennett", new Double[] { 100.0 });
        Student studB = new Student("John", "Bennett", new Double[] { 50.0 });

        classroom.addStudent(stud);
        classroom.addStudent(studB);
        classroom.removeStudent("John", "Bennett");
        Assert.assertTrue(classroom.getStudents().size() == 1);
    }

    @Test
    public void getLetterGrade() {
        Classroom classroom = new Classroom();
        ArrayList<Student> randStuds = Student.createRandomStudents(100);
        Double score = 100.0;
        for (Student s : randStuds) {
            for (int i = 0; i < 2; i++) {
                s.addExamScore(score);
            }
            score--;
        }
        classroom.addAllStudents(randStuds);
        Map<Character, ArrayList<Student>> mapp = classroom.getLetterGrade();
        ArrayList<Student> allStudents = new ArrayList<>();
        int aScores = 0;
        int bScores = 0;
        int cScores = 0;
        int dScores = 0;
        int fScores = 0;
        for (Map.Entry<Character, ArrayList<Student>> entry : mapp.entrySet()) {
            allStudents.addAll(entry.getValue());
            switch (entry.getKey()) {
                case 'A':
                    aScores += entry.getValue().size();
                    break;
                case 'B':
                    bScores += entry.getValue().size();
                    break;
                case 'C':
                    cScores += entry.getValue().size();
                    break;
                case 'D':
                    dScores += entry.getValue().size();
                    break;
                case 'F':
                    fScores += entry.getValue().size();
                    break;
                default:
                    break;
            }
        }
        Assert.assertTrue(allStudents.size() == 100);
        Assert.assertTrue(aScores == 11);
        Assert.assertTrue(bScores == 19);
        Assert.assertTrue(cScores == 21);
        Assert.assertTrue(dScores == 39);
        Assert.assertTrue(fScores == 10);
    }

}

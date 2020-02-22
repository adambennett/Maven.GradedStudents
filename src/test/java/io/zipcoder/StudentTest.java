package io.zipcoder;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class StudentTest {

    @Test
    public void getCurrentClass() {
        Classroom expected = new Classroom(20);
        Student student = new Student("", "");
        expected.addStudent(student);
        Classroom actual = student.getCurrentClass();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setCurrentClass() {
        Classroom expected = new Classroom(20);
        Student student = new Student("", "");
        student.setCurrentClass(expected);
        Classroom actual = student.getCurrentClass();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCurrentLetterGrade() {
        Classroom classroom = new Classroom(1);
        Student stud = new Student("", "");
        stud.addExamScore(100.0);
        classroom.addStudent(stud);
        Character expected = 'A';
        Character actual = 'A';
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTestScores() {
        Student stud = new Student("", "");
        stud.addExamScore(100.0);
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(100.0);
        ArrayList<Double> actual = stud.getTestScores();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getNumberOfExamsTaken() {
        Student stud = new Student("", "");
        stud.addExamScore(100.0);
        Integer expected = 1;
        Integer actual = stud.getNumberOfExamsTaken();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExamScores() {
        Student stud = new Student("", "");
        stud.addExamScore(100.0);
        String expected = "Exam 1 -> 100.0\n";
        String actual = stud.getExamScores();
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getAverageExamScore() {
        Student stud = new Student("", "");
        stud.addExamScore(100.0);
        stud.addExamScore(50.0);
        Double expected = 75.0;
        Double actual = stud.getAverageExamScore();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testToString() {
        Classroom classroom = new Classroom(1);
        Student stud = new Student("Adam", "Bennett");
        stud.addExamScore(100.0);
        classroom.addStudent(stud);
        String expected = "Adam Bennett\n> Current Grade: A\n> Average Score: 100.0\n> Exam Scores: \nExam 1 -> 100.0\n";
        String actual = stud.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testEquals() {
        Student stud = new Student("Adam", "Bennett");
        Student studB = new Student("Adam", "Smith");
        Student studC = new Student("John", "Bennett");
        Student studD = new Student("Adam", "Bennett");
        Assert.assertTrue(stud.equals(studD));
        Assert.assertTrue(!stud.equals(studB));
        Assert.assertTrue(!stud.equals(studC));
    }

    @Test
    public void compareTo() {
        Student stud = new Student("Adam", "Bennett");
        Student studB = new Student("Adam", "Smith");
        Student studC = new Student("John", "Bennett");
        Student studD = new Student("Adam", "Bennett");

        stud.addExamScore(100.0);
        studB.addExamScore(90.0);
        studC.addExamScore(100.0);
        studD.addExamScore(60.0);

        int expectedCompareA = -9;
        int expectedCompareB = 1;
        int expectedCompareC = -1;
        int expectedCompareD = -1;
        int actualCompareA = stud.compareTo(studC);
        int actualCompareB = stud.compareTo(studD);
        int actualCompareC = studB.compareTo(studC);
        int actualCompareD = studD.compareTo(studB);

        Assert.assertEquals(expectedCompareA, actualCompareA);
        Assert.assertEquals(expectedCompareB, actualCompareB);
        Assert.assertEquals(expectedCompareC, actualCompareC);
        Assert.assertEquals(expectedCompareD, actualCompareD);
    }

    @Test
    public void createRandomStudents() {
        ArrayList<Student> studs = Student.createRandomStudents(10);
        Assert.assertTrue(studs.size() == 10);
    }

}
package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;

    public Student(String firstName, String lastName, Double[] testScores) {
        this.examScores = new ArrayList<>();
        this.examScores.addAll(Arrays.asList(testScores));
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Double> getTestScores() {
        return examScores;
    }

    public Integer getNumberOfExamsTaken() {
        return examScores.size();
    }

    public String getExamScores() {
        String toRet = "";
        int counter = 1;
        for (Double d : this.examScores) {
            toRet += "Exam " + counter + " -> " + d + "\n";
        }
        return toRet;
    }

    public void addExamScore(Double d) {
        this.examScores.add(d);
    }

    public void setExamScore(int examNumber, Double newScore) {
        if (examNumber < this.examScores.size()) {
            this.examScores.set(examNumber, newScore);
        }
    }

    public Double getAverageExamScore() {
        Double sum = 0.0;
        int noOf = this.examScores.size();
        for (Double d : this.examScores) {
            sum += d;
        }
        return sum/noOf;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + "\n> Average Score: " + getAverageExamScore() + "\n> Exam Scores: " + getExamScores();
    }


    @Override
    public int compareTo(Student o) {
        if (((Student) o).getAverageExamScore() > this.getAverageExamScore()) {
            return -1;
        } else if (((Student) o).getAverageExamScore() < this.getAverageExamScore()) {
            return 1;
        } else {
            return (this.getFirstName() + this.getLastName()).compareTo(o.getFirstName() + o.getLastName());
        }
    }
}

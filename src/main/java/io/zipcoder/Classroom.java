package io.zipcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

public class Classroom {

    private Student[] students;
    private TreeMap<Student, Double> treeHouse;

    public Classroom(int maxNumberOfStudents) {
        students = new Student[maxNumberOfStudents];
        treeHouse = new TreeMap<>();
    }

    public Classroom(Student[] arr) {
        treeHouse = new TreeMap<>();
        students = new Student[arr.length];
        for (int i = 0; i < arr.length; i++) {
            students[i] = arr[i];
        }
    }

    public Classroom() {
        students = new Student[30];
        treeHouse = new TreeMap<>();
    }

    public Student[] getStudents() {
        return this.students;
    }

    public Double getAverageExamScore() {
        double sum = 0.0f;
        double noOf = 0.0f;
        for (Student s : this.students) {
            for (Double d : s.getTestScores()) {
                sum += d;
                noOf++;
            }
        }

        return sum/noOf;
    }

    public void addStudent(Student student) {
        this.students = new Student[this.students.length-1];
        this.students[this.students.length - 1] = student;
    }

    public void removeStudent(String firstName, String lastName) {
        Student[] newArr = new Student[this.students.length - 1];
        int newIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (!(this.students[i].getFirstName().equals(firstName) && this.students[i].getLastName().equals(lastName))) {
                newArr[newIndex] = this.students[i];
                newIndex++;
            }
        }
    }

    private void updateTreehouse() {
        this.treeHouse.clear();
        for (Student s : this.students) {
            this.treeHouse.put(s, s.getAverageExamScore());
        }
    }

    public Student[] getStudentsByScore() {
        updateTreehouse();
        Student[] toRet = new Student[this.students.length];
        int index = 0;
        for (Map.Entry<Student, Double> i : this.treeHouse.entrySet()) {
            toRet[index] = i.getKey();
        }
        return toRet;
    }

    public Map<Student, Character> getLetterGrade() {
        Map<Student, Character> toRet = new HashMap<>();
        // Highest avg
        // Highest avg * (1-0.1)                                    = 10% mark
        // [Highest avg * (1-0.11)] -range- [Highest avg * (1-0.29)] = 11-29% mark
        // [Highest avg * (1-0.30)] -range- [Highest avg * (1-0.50)] = 30-50% mark
        // [Highest avg * (1-0.51)] -range- [Highest avg * (1-0.89)] = 51-89% mark
        // [Highest avg * (1-0.9)] -range- [0]                       = 90-100% mark


        int highestAvg = -1;
        for (Map.Entry<Student, Double> i : this.treeHouse.entrySet()) {
            Logger.getGlobal().info("Printing entry from treehouse: " + i.getKey() + ", " + i.getValue());
        }
        return toRet;
    }

}

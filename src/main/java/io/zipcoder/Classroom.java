package io.zipcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

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

        return toRet;
    }

}

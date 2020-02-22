package io.zipcoder;

import java.util.*;
import java.util.logging.Logger;

public class Classroom {

    private TreeMap<Student, Double> treeHouse;
    private ArrayList<Student> students;

    public Classroom(int maxNumberOfStudents) {
        students = new ArrayList<>();
        treeHouse = new TreeMap<>();
    }

    public Classroom(Student[] arr) {
        treeHouse = new TreeMap<>();
        students = new ArrayList<>();
        if (arr != null) {
            for (Student s : arr) {
                students.add(s);
            }
        }
    }

    public Classroom() {
        students = new ArrayList<>();
        treeHouse = new TreeMap<>();
    }

    public ArrayList<Student> getStudents() {
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
        student.setCurrentClass(this);
        this.students.add(student);
        updateTreehouse();
    }

    public void addAllStudents(ArrayList<Student> e) {
        for (Student s : e) {
            s.setCurrentClass(this);
        }
        this.students.addAll(e);
        updateTreehouse();
    }

    public void removeStudent(String firstName, String lastName) {
        ArrayList<Student> toRemove = new ArrayList<>();
        for (Student s : this.students) {
            if (s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)) {
                toRemove.add(new Student(firstName, lastName));
            }
        }

        for (Student s : toRemove) {
            this.students.remove(s);
        }

        updateTreehouse();
    }

    private void updateTreehouse() {
        this.treeHouse.clear();
        for (Student s : this.students) {
            this.treeHouse.put(s, s.getAverageExamScore());
        }
    }

    public ArrayList<Student> getStudentsByScore() {
        updateTreehouse();
        ArrayList<Student> toRet = new ArrayList<>();
        int index = 0;
        for (Map.Entry<Student, Double> i : this.treeHouse.entrySet()) {
            toRet.add(i.getKey());
        }
        return toRet;
    }

    private Double getHighestAvg() {
        Double highestAvg = 0.0;
        for (Student i : this.treeHouse.descendingKeySet()) {
            highestAvg = i.getAverageExamScore();
            break;
        }
        return highestAvg;
    }

    public Map<Character, ArrayList<Student>> getLetterGrade() {
        Map<Character, ArrayList<Student>> toRet = new HashMap<>();
        ArrayList<ArrayList<Student>> listIter = new ArrayList<>();
        ArrayList<Student> aScores = new ArrayList<>();
        ArrayList<Student> bScores = new ArrayList<>();
        ArrayList<Student> cScores = new ArrayList<>();
        ArrayList<Student> dScores = new ArrayList<>();
        ArrayList<Student> fScores = new ArrayList<>();
        Double highestAvg = getHighestAvg();
        Double topPercentile = highestAvg * (1 - 0.1);
        Double secondPercentFloor = highestAvg * (1 - 0.29);
        Double thirdPercentFloor = highestAvg * (1 - 0.5);
        Double fourthPercentFloor = highestAvg * (1 - 0.89);
        for (Student i : this.treeHouse.descendingKeySet()) {
            Double score = i.getAverageExamScore();
            if (score >= topPercentile) {
                aScores.add(i);
            } else if (score >= secondPercentFloor) {
                bScores.add(i);
            } else if (score >= thirdPercentFloor) {
                cScores.add(i);
            } else if (score >= fourthPercentFloor) {
                dScores.add(i);
            } else {
                fScores.add(i);
            }
        }
        toRet.put('A', aScores);
        toRet.put('B', bScores);
        toRet.put('C', cScores);
        toRet.put('D', dScores);
        toRet.put('F', fScores);
        return toRet;
    }

}

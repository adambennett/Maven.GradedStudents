package io.zipcoder;

import java.util.*;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;
    private Classroom currentClass;

    public Student(String firstName, String lastName, Double[] testScores) {
        this.examScores = new ArrayList<>();
        this.examScores.addAll(Arrays.asList(testScores));
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<>();
    }

    public Classroom getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(Classroom currentClass) {
        this.currentClass = currentClass;
    }

    public Character getCurrentLetterGrade() {
        if (this.currentClass != null) {
            Map<Character, ArrayList<Student>> gett = this.currentClass.getLetterGrade();
            for (Map.Entry<Character, ArrayList<Student>> i : gett.entrySet()) {
                if (i.getValue().contains(this)) {
                    return i.getKey();
                }
            }
        }
        return 'I';
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
            counter++;
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
        String name = this.firstName + " " + this.lastName + "\n> Average Score: " + getAverageExamScore() + "\n> Exam Scores: \n" + getExamScores();
        if (this.currentClass != null) {
           name = this.firstName + " " + this.lastName + "\n>Current Grade: " + getCurrentLetterGrade() + "\n> Average Score: " + getAverageExamScore() + "\n> Exam Scores: \n" + getExamScores();
        }
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            return (((Student) o).getFirstName().equals(this.firstName) && ((Student) o).getLastName().equals(this.lastName)) ? true : false;
        } else {
            return super.equals(o);
        }
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

    public static ArrayList<Student> createRandomStudents(int amt) {
        ArrayList<Student> toRet = new ArrayList<>();
        while (toRet.size() < amt) {
            toRet.add(new Student(randomIdentifier(), randomIdentifier()));
        }
        return toRet;
    }

    public static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        Random rand = new Random();
        String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Set<String> identifiers = new HashSet<String>();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}

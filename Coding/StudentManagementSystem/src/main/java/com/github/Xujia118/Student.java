package com.github.Xujia118;

import java.util.Objects;

public class Student {
    private String id;
    private String name;
    private int score;

    public Student(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("ID: %-5s | Name: %-10s | Score: %d", id, name, score);
    }
}
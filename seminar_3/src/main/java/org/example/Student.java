package org.example;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.*;
@JsonIgnoreProperties(value = "GPA")
public class Student implements Serializable {
    public String name;
    public int age;
    @JsonIgnore
    public transient double GPA;

    public Student() {}
    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;

        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
//    public double getGPA() {
//        return GPA;
//    }
}

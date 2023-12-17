package org.example;

    /* 1. Разработайте класс Student с полями String name, int age,
    transient double GPA (средний балл). Обеспечьте поддержку
    сериализации для этого класса. Создайте объект класса
    Student и инициализируйте его данными. Сериализуйте этот
    объект в файл. Десериализуйте объект обратно в программу
    из файла. Выведите все поля объекта, включая GPA, и обсудите,
    почему значение GPA не было сохранено/восстановлено.
    2. * Выполнить задачу 1 используя другие типы сериализаторов
    (в xml и json документы). */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello world!");
        Student student = new Student("Василий", 21, 4.5);

        try (FileOutputStream fos = new FileOutputStream("Student.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(student);
            System.out.println("Объект " + student.toString() + " сериализован");
        }

        try (FileInputStream fis = new FileInputStream("Student.bin");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            student = (Student) ois.readObject();
            System.out.println("Объект " + student.toString() + " десериализован");
        }
        student.setGPA(4.7);
        WriteDataToFile.WriteToXML(student);
        student = ReadDataFromFile.ReadFromXML(new File("Student.xml"));
        System.out.println("Объект " + student.toString() + " десериализован из файла XML");
        student.setGPA(4.8);
        WriteDataToFile.WriteToJSON(student);
        student = ReadDataFromFile.ReadFromJSON(new File("Student.json"));
        System.out.println("Объект " + student.toString() + " десериализован из файла JSON");
    }
}
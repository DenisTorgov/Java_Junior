package org.example.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {
    static int animalsNum = 10;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ArrayList<Animal> animals = Controller.createAnimals(animalsNum);
        for (Animal a: animals) {
            System.out.println("-------------------------------");
            Controller.classAnalizer(a);
            System.out.println("Ivocation \"makeSoud\" method: ");
            Method method = a.getClass().getDeclaredMethod("makeSound");
            method.setAccessible(true);
            method.invoke(a);
        }
    }
}
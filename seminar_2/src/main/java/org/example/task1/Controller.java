package org.example.task1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Controller {
    static Random rnd = new Random();
    static int dogsNum = 1;
    static int catsNum = 1;
    public static ArrayList<Animal> createAnimals(int animalsNum){
        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < animalsNum; i++) {
            if (rnd.nextInt(0, 2) == 0) {
                StringBuilder sb = new StringBuilder("Cat " + catsNum++);
                animals.add( new Cat(sb.toString(), rnd.nextInt(0, 12)));
            } else {
                StringBuilder sb = new StringBuilder("Dog " + dogsNum++);
                animals.add( new Dog(sb.toString(), rnd.nextInt(0, 15), rnd.nextInt(0, 25)));
            }
        }
        return animals;
    }
    public static void classAnalizer(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field[] fieldsSuper = obj.getClass().getSuperclass().getDeclaredFields();
        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println("Class name: " + obj.getClass().getSimpleName());
        Field field = obj.getClass().getSuperclass().getDeclaredField("name");
        field.setAccessible(true);
        System.out.println("Object name: " + field.get(obj));
        System.out.println("Object fields: ");
        for (Field f: fieldsSuper) {
            f.setAccessible(true);
            System.out.println(f.toString());
        }
        for (Field f: fields) {
            f.setAccessible(true);
            System.out.println(f.toString());
        }
        System.out.println("Object methods " + Arrays.toString(obj.getClass().getDeclaredMethods()));
    }

}

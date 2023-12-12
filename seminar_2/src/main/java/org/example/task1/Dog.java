package org.example.task1;
public class Dog extends Animal {
    private int sticks;

    public Dog (String name, int age, int sticks) {
        this.name = name;
        this.age = age;
        this.sticks = sticks;
    }
    public void makeSound() {
        System.out.println("Woof-Woof");
    }
    public int getStick(Dog dog) {
        return ++sticks;
    }
}
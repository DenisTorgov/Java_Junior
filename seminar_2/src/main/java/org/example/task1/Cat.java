package org.example.task1;
public class Cat extends Animal {
    private boolean claws;

    public Cat (String name, int age) {
        this.name = name;
        this.age = age;
        claws = false;
    }
    public void makeSound() {
        System.out.println("Meow");
    }
    public void getClaws(Cat cat) {
        claws = true;
    }
    public void hideClaws(Cat cat) {
        claws = false;
    }

}

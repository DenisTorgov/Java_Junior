package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> listStream = list.stream()
                .filter(val -> (val % 2 == 0))
                .toList();
        int sum = list.stream()
                .filter(val -> (val % 2 == 0))
                .filter(val -> val != null)
                .mapToInt(val -> val)
                .sum();
        System.out.println(listStream);
        System.out.println(sum);
        System.out.println("Среднее часло четных чисел массива: " + sum/listStream.size());
    }
}
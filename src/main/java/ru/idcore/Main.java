package ru.idcore;

import ru.idcore.MyCalculator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        int size = 1_000_000;
        int[] integers = new int[size];
        int min = 0;
        int max = 100;
        Date timerStart;
        Date timerEnd;
        long time;


        Function<int[], Long> arraySum = MyCalculator::getSumOfArray;
        Function<int[], Double> arrayMiddle = MyCalculator::getMiddleOfArray;

        for (int i = 0; i < size; i++) {
            integers[i] = min + (int) (Math.random() * ((max - min) + 1));
        }

        //System.out.println(Arrays.toString(integers));

        timerStart = new Date();
        long result = arraySum.apply(integers);
        timerEnd = new Date();
        time = (timerEnd.getTime() - timerStart.getTime());
        System.out.printf("Сумма элементов массива из %d чисел: %d\n", size, result);
        System.out.printf("Время расчета: %d мс\n", time);
        System.out.println();

        timerStart = new Date();
        double middle = arrayMiddle.apply(integers);
        timerEnd = new Date();
        time = (timerEnd.getTime() - timerStart.getTime());
        System.out.printf("Среднеарифметическое чисел массива: %f\n", middle);
        System.out.printf("Время расчета: %d мс\n", time);
        System.out.println();


        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        timerStart = new Date();
        Long forkJoinSum = forkJoinPool.invoke(new ArraySumByForkJoin(integers));
        timerEnd = new Date();
        time = (timerEnd.getTime() - timerStart.getTime());
        System.out.printf("Сумма элементов массива из %d чисел, рассчитанных с применением fork join: %d\n", size, forkJoinSum);
        System.out.printf("Время расчета: %d мс\n", time);
        System.out.println();

        Double middleSumForkJoin = forkJoinPool.invoke(new MIddleArraySumByForkJoin(integers));
        System.out.printf("Среднеарифметическое чисел массива, рассчитанное с применением fork join: %f\n", middle);
        System.out.printf("Время расчета: %d мс\n", time);
        System.out.println();


    }
}

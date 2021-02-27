package ru.idcore;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.List;
import java.util.function.Function;

public class MyCalculator {
    public static long getSumOfArray(int[] arr) {
        long result = 0;

        for (int value : arr) {
            result += value;
        }

        return result;
    }

    public static double getMiddleOfArray(int[] arr) {
        double result = 0;
        try {
            if (arr.length > 0) {
                result =  (double) getSumOfArray(arr) / arr.length;
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return result;
    }
}

package ru.idcore;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ArraySumByForkJoin extends RecursiveTask<Long> {
    int[] ints;

    public ArraySumByForkJoin(int[] ints) {
        this.ints = ints;
    }

    @Override
    protected Long compute() {
        int length = ints.length;
        int middle = ints.length / 2;
        switch (length) {
            case 0:
                return 0L;
            case 1:
                return (long) ints[0];
            case 2:
                return (long) (ints[0] + ints[1]);
            default:
                int[] leftArr = Arrays.copyOfRange(ints, 0, middle);
                int[] rightArr = Arrays.copyOfRange(ints, middle, ints.length);
                //System.out.println(Arrays.toString(leftArr));
                //System.out.println(Arrays.toString(rightArr));
                ArraySumByForkJoin left = new ArraySumByForkJoin(leftArr);
                ArraySumByForkJoin right = new ArraySumByForkJoin(rightArr);

                left.fork();
                right.fork();
                return left.join() + right.join();
        }
    }
}

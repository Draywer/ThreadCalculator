package ru.idcore;

import java.util.concurrent.RecursiveTask;

public class MIddleArraySumByForkJoin extends RecursiveTask<Double> {
    int[] ints;

    public MIddleArraySumByForkJoin(int[] ints) {
        this.ints = ints;
    }

    @Override
    protected Double compute() {
        ArraySumByForkJoin arraySumByForkJoin = new ArraySumByForkJoin(ints);

        return (double) arraySumByForkJoin.compute() / ints.length;
    }
}

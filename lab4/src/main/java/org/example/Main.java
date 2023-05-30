package org.example;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 10000;
        int[] input1 = generateRandomArray(n);
        int[] input2 = generateRandomArray(n);
        int[] resultSync = new int[n];
        int[] resultParallel = new int[n];
        int sleep = 0; // Значення затримки у мілісекундах

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            resultSync[i] = multiplyWithDelay(input1[i], input2[i], sleep);
        }
        long syncTime = System.currentTimeMillis() - time1;
        System.out.println("Sync Time: " + syncTime);

        long time2 = System.currentTimeMillis();
        Arrays.parallelSetAll(resultParallel, i -> multiplyWithDelay(input1[i], input2[i], sleep));
        long parallelTime = System.currentTimeMillis() - time2;
        System.out.println("Parallel Time: " + parallelTime);
    }

    private static int[] generateRandomArray(int n) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(101); // Випадкове число від 0 до 100
        }
        return array;
    }

    private static int multiplyWithDelay(int a, int b, int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return a * b;
    }
}

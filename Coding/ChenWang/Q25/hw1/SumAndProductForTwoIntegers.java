package com.chuwa.exercise.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SumAndProductForTwoIntegers {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 5;

        // Asynchronously calculate sum
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating sum in thread: " + Thread.currentThread().getName());
            return num1 + num2;
        });

        // Asynchronously calculate product
        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Calculating product in thread: " + Thread.currentThread().getName());
            return num1 * num2;
        });

        try {
            // Get and print results
            Integer sum = sumFuture.get();
            Integer product = productFuture.get();

            System.out.println("Sum: " + sum);
            System.out.println("Product: " + product);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

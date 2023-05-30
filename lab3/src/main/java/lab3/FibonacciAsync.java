package lab3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.Scanner;

public class FibonacciAsync {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Число n: ");
        int n = in.nextInt();

        // Створюємо CompletableFuture для асинхронного обчислення числа Фібоначчі
        CompletableFuture<Long> fibonacciFuture = CompletableFuture.supplyAsync(() -> fibonacci(n));

        System.out.println("Очікування завершення обчислень...");

        try {
            // Очікуємо, поки CompletableFuture завершиться і отримуємо результат
            long result = fibonacciFuture.get();

            // Виводимо отримане значення
            System.out.println("Результат: " + result);        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();        }
    }

    private static long fibonacci(int n) {
        if (n <= 1) {
            return n;}
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);}
    }
}
package org.example;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static volatile int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new LoggingTask("Thread 1", 250));
        Thread thread2 = new Thread(new LoggingTask("Thread 2", 500));
        Thread thread3 = new Thread(new LoggingTask("Thread 3", 1000));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class LoggingTask implements Runnable {
        private final String threadName;
        private final long interval;

        LoggingTask(String threadName, long interval) {
            this.threadName = threadName;
            this.interval = interval;
        }

        @Override
        public void run() {
            try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
                while (counter < 240) {
                    Thread.sleep(interval);

                    synchronized (Main.class) {
                        counter++;
                    }

                    String currentTime = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());

                    writer.println(threadName + ": " + currentTime + " - Counter: " + counter);
                    writer.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
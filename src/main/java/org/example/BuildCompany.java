package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Создать модель работы строительной компании, у которой время от времени появляются заказы на новые объекты.
 * При этом:
 * а) Строительство ведет одна постоянная бригада
 * b) Строительство ведут несколько бригад одновременно
 * c) Бригады создаются по количеству текущих объектов, а после распускаются.
 */
public class BuildCompany {
    public static void main(String[] args) {

        ExecutorService executorSingle = Executors.newSingleThreadExecutor();
        ExecutorService executorFixed = Executors.newFixedThreadPool(2);
        ExecutorService executorCached = Executors.newCachedThreadPool();

//        build(executorSingle);
//        build(executorFixed);
        build(executorCached);

    }

    public static void build(ExecutorService executor) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> System.out.println("Build a new facility"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Time: " + (System.currentTimeMillis() - start));
        executor.shutdown();
    }
}

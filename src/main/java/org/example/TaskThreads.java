package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * First level: 1. Имеется список. Один поток проходит итератором по списку и печатает его значения,
 * второй поток в это же время меняет значение / удаляет элемент из списка.
 * Продемонстрировать, что будет, если список:
 */
public class TaskThreads {
    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(0, 1000).boxed().collect(Collectors.toList());

        ArrayList<Integer> listArrayList = new ArrayList<>(list);
        List<Integer> listSynchronized = Collections.synchronizedList(list);
        CopyOnWriteArrayList<Integer> listCopyWrite = new CopyOnWriteArrayList<>(list);
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue(list);

        // ArrayList
//        new Thread(()-> change(listArrayList)).start();
//        new Thread(()-> print(listArrayList)).start();

        //  SynchronizedList
//        new Thread(()-> change(listSynchronized)).start();
//        new Thread(()-> print(listSynchronized)).start();

        //     ConcurrentLinkedQueue
//        new Thread(()-> delete(queue)).start();
//        new Thread(()-> print(queue)).start();

        // CopyOnWriteArrayList
        new Thread(() -> change(listCopyWrite)).start();
        new Thread(() -> print(listCopyWrite)).start();
    }

    public static void print(Collection<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    public static void change(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) * 2);
        }
        ;
    }

    public static void delete(Queue<Integer> queue) {
        for (int i = 0; i < queue.size(); i++) {
            queue.remove();
        }
        ;
    }
}
package com.petrov;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int counter = 0;
    private final int MAX_VALUE = 10;

    public void count(Lock lock, String thread) {
        for (int i = 0; i < MAX_VALUE; i++) {
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (counter < MAX_VALUE) {
                counter++;
                System.out.println(counter + " -> " + thread);
            } else {
                lock.unlock();
                break;
            }
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Counter counter = new Counter();

        Lock lock = new ReentrantLock(true);

        new Thread(() -> counter.count(lock, Thread.currentThread().getName())).start();
        new Thread(() -> counter.count(lock, Thread.currentThread().getName())).start();
    }


}

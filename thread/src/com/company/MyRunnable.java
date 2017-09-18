package com.company;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

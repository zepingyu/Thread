package com.company;

//第一种 Thread
/*public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 25; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 10) {
                Thread myThread1 = new MyThread();     // 创建一个新的线程  myThread1  此线程进入新建状态
                Thread myThread2 = new MyThread();       // 创建一个新的线程 myThread2 此线程进入新建状态
                myThread1.start();                       // 调用start()方法使得线程进入就绪状态
                myThread2.start();                       // 调用start()方法使得线程进入就绪状态

                Runnable myRunnable = new MyRunnable();  // 创建一个Runnable实现类的对象
                Thread rthread1 = new Thread(myRunnable);// 将myRunnable作为Thread target创建新的线程
                Thread rthread2 = new Thread(myRunnable);
                rthread1.start();                        // 调用start()方法使得线程进入就绪状态
                rthread2.start();
            }
        }
    }
}*/
/*
//第二种 Runnable
public class ThreadTest {

    static class MyRunnable implements Runnable {
        private int i = 0;

        @Override
        public void run() {
            System.out.println("in MyRunnable run");
            for (i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }

    static class MyThread extends Thread {

        private int i = 0;

        public MyThread(Runnable runnable){
            super(runnable);
        }

        @Override
        public void run() {
            System.out.println("in MyThread run");
            for (i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Runnable myRunnable = new MyRunnable();
                Thread thread = new MyThread(myRunnable);
                thread.start();
            }
        }
    }
}
*/

//第三种 Callable,FutureTask
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    static class MyCallable implements Callable <Integer> {
        private int i = 0;

        // 与run()方法不同的是，call()方法具有返回值
        @Override
        public Integer call() {
            int sum = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                sum += i;
            }
            return sum;
        }

    }

    static class MyCallabletTest implements  Callable <String> {
        private int i = 0;

        @Override
        public String call() throws Exception {
            String sum = "";

            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) {

        /*Callable<Integer> myCallable = new MyCallable();               // 创建MyCallable对象
        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable); //使用FutureTask来包装MyCallable对象

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(ft);                       //FutureTask对象作为Thread对象的target创建新的线程
                thread.start();                                       //线程进入到就绪状态
            }
        }

        System.out.println("主线程for循环执行完毕..");

        try {
            int sum = ft.get();                                        //取得新创建的新线程中的call()方法返回的结果
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        Callable<String> myCallablet = new MyCallabletTest();
        FutureTask<String> ft = new FutureTask<String>(myCallablet);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(ft);                       //FutureTask对象作为Thread对象的target创建新的线程
                thread.start();                                       //线程进入到就绪状态
                Thread thread1 = new Thread(ft);
                thread1.start();
            }
        }
        System.out.println("主线程for循环执行完毕..");

    }
}

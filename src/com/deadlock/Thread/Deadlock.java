package com.deadlock.Thread;


public class Deadlock {
    private static Object resource1=new Object();
    private static Object resource2=new Object();
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            synchronized (resource1) {
                System.out.println("Thread1 holding resource1 ...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread1 waiting for resource2...");
            }
            synchronized (resource2){
                System.out.println("Thread1 holding resource1 and resource2...");
            }

        });

        Thread t2=new Thread(()->{
            synchronized (resource2){
                System.out.println("Thread2 holding resource2 ...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread2 waiting for resource1...");
            }
            synchronized (resource1){
                System.out.println("Thread2 holding resource1 and resource2...");
            }

        });
        t1.start();
        t2.start();


    }

}

package com.nivelle.programming.java2e.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();

    public void execute(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"do something synchronize");
            try {
                anotherLock();
                Thread.sleep(5000l);

            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"interrupted");
                Thread.currentThread().interrupt();
            }

        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String args[]){
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(()->{reentrantLockTest.execute();}).start();

        new Thread(()->{reentrantLockTest.execute();}).start();
    }

    public void anotherLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " invoke anotherLock");
        } finally {
            lock.unlock();
        }
    }

}

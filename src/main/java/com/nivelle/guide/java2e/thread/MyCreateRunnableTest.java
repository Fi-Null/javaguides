package com.nivelle.guide.java2e.thread;

import java.lang.Runnable;

class MyCreateRunnable implements Runnable{
  private int i=0;
  @Override
  public void run(){
    for(i=0;i<100;i++){
    	System.out.println(Thread.currentThread().getName() + " " + i);
    }
  }
}
public class MyCreateRunnableTest{
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i==30){
				 Runnable myRunnable = new MyRunnable();
				 Thread thread1 = new Thread(myRunnable); 
				 // 将myRunnable作为Thread target创建新的线程              
				 Thread thread2 = new Thread(myRunnable);
				 thread1.start();
				 thread2.start();
			}
		}
	}
}
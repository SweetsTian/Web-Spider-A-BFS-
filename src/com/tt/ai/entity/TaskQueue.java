package com.tt.ai.entity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.tt.ai.thread.BFSTask;

/**
 * To store the task(Runnable)
 * @author Tian  
 */
public class TaskQueue {
	public static  BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();

	/**
	 * put a task to the taskQueue and wait thread to do it.
	 * */
	public static void execute(Runnable task){
		synchronized (taskQueue) {
			taskQueue.offer(task);
			taskQueue.notify();
		}
	}

	/**
	 * Add a task queue to the taskQueue and wait thread to do it.
	 * */
	public static void execute(Runnable[] task){
		synchronized (taskQueue) {
			for(Runnable t:task)
				taskQueue.offer( t);
			taskQueue.notify();
		}
	}


	/**
	 * get the size of task queue
	 * */
	public static int getWaitTaskNumbre(){
		return taskQueue.size();
	}

	public static boolean isEmpty() {
		// TODO Auto-generated method stub

		return taskQueue.isEmpty();
	}

	public static void clear() {
		// TODO Auto-generated method stub
		taskQueue.clear();
	}

	public static Runnable poll() {
		// TODO Auto-generated method stub
		return taskQueue.poll();
	}


	public static int size() {
		return taskQueue.size();
	}

	public static void waits(int i) throws InterruptedException{
		try{
			taskQueue.wait(i);
		}catch(Exception e){

		}
	}

}

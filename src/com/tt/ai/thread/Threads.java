package com.tt.ai.thread;

import com.tt.ai.entity.TaskQueue;

/**
 * The Threads class extends the Thread class is the thread in ThreadsPool 
 * @author Tian
 */
public class Threads extends Thread{

	// whether the WorkThread is vaild
	int i;
	public Threads(int i){
		this.i=i;
		//System.out.println("i "+ i);
	}
	private boolean isRunning = true;

	/**
	 * Run a task in task queue 
	 */
	public void run(){
		Runnable r = null;
		while(isRunning){
			synchronized (TaskQueue.class) {
				while(isRunning && TaskQueue.isEmpty()){ //the taskQueue is empty
					try{
						TaskQueue.waits(20);
					}catch(InterruptedException e){
					}
				}
				if(!TaskQueue.isEmpty())
					r = TaskQueue.poll(); //get task from taskQueue
			}
			if(r != null){
				r.run(); // execute the task
			}
			r=null;
		}
	}

	/**
	 * stop the run
	 */
	public void stopWorker(){
		isRunning = false;
	}
}



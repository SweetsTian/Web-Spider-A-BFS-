package com.tt.ai.thread;

import com.tt.ai.entity.TaskQueue;

public class ThreadsPool {

	//The number of threads
	private static int worker_num = 4;
	//work thread group
	private static Threads[] workThreads;
	//
	private static volatile int finished_task = 0;
	//Task Queue, the inqueue actually, it store the user requirement, the LinkedBlockingQueue is a safe threads 
	//private static  BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();
	private static ThreadsPool threadsPool;
	private static boolean thread_Status=false;

	public static boolean isThread_Status() {
		return thread_Status;
	}
	public static void changeThreadStatus(){
		if(thread_Status==false)
			thread_Status=true;
		else
			thread_Status=false;
	}
	public static void StartWorkThread(){
		if(!thread_Status){
			changeThreadStatus();
			workThreads = new Threads[worker_num];
			for(int i =0; i < worker_num ; i++){
				workThreads[i] = new Threads(i);
				//begin the work thread
				workThreads[i].start();
			}
		}
	}
	/**
	 * Create thread pool
	 * @param worker_num is the number of threads in Thread pool
	 * */
	private ThreadsPool(int worker_num){
		ThreadsPool.worker_num = worker_num;
		workThreads = new Threads[worker_num];
		for(int i =0; i < worker_num ; i++){
			workThreads[i] = new Threads(i);
			//begin the work thread
			workThreads[i].start();
		}
	}

	/**
	 * get the threadPool which work threads' number is the default.
	 * */
	public static ThreadsPool getThreadsPool(){
		return getThreadsPool(ThreadsPool.worker_num);
	}

	/**
	 * get a threadPool
	 * @param worker_num1 is the work thread numbers. if worker_num1<=0
	 *        use the default number.
	 * */
	public static ThreadsPool getThreadsPool(int worker_num1){
		if(worker_num1 <= 0)
			worker_num1 = ThreadsPool.worker_num;
		if(threadsPool == null)
			threadsPool = new ThreadsPool(worker_num1);
		return threadsPool;
	}

	/**
	 * get the number of work threads
	 * */
	public int getWorkThreadNumber(){
		return worker_num;
	}

	

//	public String getInformation(){
//		return "WorkThread number:" + worker_num + "  finished task number:"
//				+ finished_task + "  wait task number:" + TaskQueue.getWaitTaskNumbre();
//	}

	public static void destroy(){
//		while(!BFSTaskQueue.isEmpty()){
//			try{
//				Threads.sleep(10);
//			} catch (InterruptedException e){
//				//e.printStackTrace();
//			}
//		}
		//stop the work thread
		try{
		for(int i = 0 ; i < worker_num; i++){
			((Threads) workThreads[i]).stopWorker();
			workThreads[i] = null;
		}
		thread_Status=false;
		TaskQueue.clear(); // clear the taskQueue
		}catch(Exception e){
			
		}
	}

	


}


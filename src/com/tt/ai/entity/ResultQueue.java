package com.tt.ai.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import com.tt.ai.handler.PageComparator;

/**
 * It is the A star algorithm open queue and the queue to store the information in BFS algorithm
 * @author Tian
 */
public class ResultQueue {
	private static Vector<PageEntity> resultQueue = new  Vector<PageEntity>();

	public static PageEntity getHighest(){
		double max = 0;
		PageEntity result = null;
		Iterator<PageEntity> iter = resultQueue.iterator();
		while(iter.hasNext()){
			PageEntity p = (PageEntity) iter.next();
			if(max<=p.getScore()){
				max = p.getScore();
				result = p;
			}
		}
		return result;
	}
	
	
	
	public static boolean removeElement(Object arg0) {
		return resultQueue.removeElement(arg0);
	}



	public static void sort(){
		try{
			PageComparator comparator = new PageComparator();
			Collections.sort(resultQueue,comparator);
		}catch(Exception e){

		}
	}

	/**
	 * To judge the if the page is already in the queue
	 * @param URL is the page url
	 * @return if not return false, else return true
	 */
	public static boolean match(String URL){
		boolean flag = false;
		Iterator<PageEntity> iter = resultQueue.iterator();
		while(iter.hasNext()){
			PageEntity p = (PageEntity) iter.next();
			if(URL.equals(p.getURL())){
				flag = true;
			}
		}
		return flag;
	}

	public static boolean add(PageEntity e) {
		return resultQueue.add(e);
	}

	public static PageEntity poll() {
		return resultQueue.remove(0);
	}

	public static boolean remove(Object o) {
		return resultQueue.remove(o);
	}

	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return resultQueue.isEmpty();
	}



	public static int size() {
		return resultQueue.size();
	}

	public static void waits(int i) throws InterruptedException {
		// TODO Auto-generated method stub
		resultQueue.wait(i);
	}

}

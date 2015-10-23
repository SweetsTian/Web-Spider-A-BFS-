package com.tt.ai.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import com.tt.ai.handler.PageComparator;

/**
 * The class is the close queue of A star algorithm  
 * @author Tian
 */
public class AStarCloseQueue {
	private static Vector<PageEntity> closeQueue = new  Vector<PageEntity>();
	

	public static PageEntity getHighest(){
		double max = 0;
		PageEntity result = null;
		Iterator<PageEntity> iter = closeQueue.iterator();
		while(iter.hasNext()){
			PageEntity p = (PageEntity) iter.next();
			if(max<=p.getScore()){
				max = p.getScore();
				result = p;
			}
		}
		return result;
	}
	
	public static void add(PageEntity element) {
		closeQueue.add(element);
	}

	public static PageEntity get(int index) {
		if(index == -1){
			return null;
		}
		return closeQueue.get(index);
	}

	public static boolean isEmpty() {
		return closeQueue.isEmpty();
	}

	public static PageEntity remove(int index) {
		return closeQueue.remove(index);
	}

	public static int size() {
		return closeQueue.size();
	}
	
	

}

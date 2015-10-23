package com.tt.ai.handler;

import java.util.Comparator;

import com.tt.ai.entity.PageEntity;

/**
 * The method implements the Comparator interface and can compare two PageEntity
 * @author Tian 
 */
public class PageComparator implements Comparator{

	/**
	 * Compare two PageEntity object depends on the score If score is same then compare the URL 
	 * @param page1 is one of the page entity
	 * @param page2 is another page entity
	 */
	@Override
	public int compare(Object page1, Object page2) {
		// TODO Auto-generated method stub
		PageEntity p1 = (PageEntity)page1;
		PageEntity p2 = (PageEntity)page2;
		
		int flag = p1.getCompareScore().compareTo(p2.getCompareScore());
		if(flag==0){
			return p1.getURL().compareTo(p2.getURL());
		}else{
			return flag;
		}
		
		//return 0;
	}

}

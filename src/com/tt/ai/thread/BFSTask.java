package com.tt.ai.thread;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;

import com.tt.ai.entity.BestPage;
import com.tt.ai.entity.PageEntity;
import com.tt.ai.entity.RequestEntity;
import com.tt.ai.entity.ResultQueue;
import com.tt.ai.handler.ScoreCounter;
import com.tt.ai.handler.Terminate;

/**
 * The BFSTask implements the Runnable interface is the task thread execute  
 * @author Tian
 */
public class BFSTask implements Runnable{
	private PageEntity pageEntity;
	public BFSTask(String URL,int depth,String herf,double expectScore) {
		pageEntity = new PageEntity(URL,depth,herf,expectScore);
	}

	/**
	 * To get the score of the page
	 */
	public void run() {
		double score = 0;
		try {
			score = ScoreCounter.getScoreSetPage(pageEntity.getURL(),pageEntity.getDepth())+pageEntity.getExpectScore();
			ResultQueue.add(pageEntity);
			if(score>BestPage.getScore()){
				BestPage.setScore(score);
				BestPage.setDepth(pageEntity.getDepth());
				BestPage.setURL(pageEntity.getURL());
				BestPage.setExpectScore(pageEntity.getExpectScore());
			}// store the best page
			if(Terminate.judge(score)&&Terminate.isStop()==false){
				Terminate.setStop(true);
				System.out.println("Target Page URL is: " + BestPage.getURL());
				System.out.println("Target Page Score is: " + BestPage.getScore());
				System.out.println("Target Page Depth is: " + BestPage.getDepth());
				System.exit(0);
//				ThreadsPool.destroy();
//				System.out.println("RRRRRRRRRRresult: "+BestPage.getURL()+BestPage.getScore()+BestPage.getURL());
			}
			
//			System.out.println("URL is "+ pageEntity.getURL());
//			System.out.println("score is "+ score);
//			System.out.println("Best Score is "+BestPage.getScore());
//			System.out.println("Depth is " + pageEntity.getDepth());
//			System.out.println("Herf is " +pageEntity.getHerf());
//			System.out.println("-------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//pageEntity.setScore(score);
	}
		
}

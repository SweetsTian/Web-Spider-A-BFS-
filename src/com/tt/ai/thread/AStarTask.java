package com.tt.ai.thread;

import java.io.IOException;

import com.tt.ai.entity.AStarCloseQueue;
import com.tt.ai.entity.ResultQueue;
import com.tt.ai.entity.PageEntity;
import com.tt.ai.entity.RequestEntity;
import com.tt.ai.handler.ScoreCounter;
import com.tt.ai.handler.Terminate;

/**
 * The AStarTask implements the Runnable interface is the task thread execute  
 * @author Tian
 */
public class AStarTask implements Runnable{

	private PageEntity pageEntity;


	public AStarTask(String URL, int depth,String herf,double expectScore, int parentLocation) {
		pageEntity = new PageEntity(URL, depth, herf, expectScore);
		pageEntity.setParentIndex(parentLocation);
	}


	/**
	 * Get the page's score and add it to the open queue
	 */
	public void run() {
		// TODO Auto-generated method stub

		double score = 0;
		try {
			// get the page score
			score = ScoreCounter.getScore(pageEntity.getURL(),pageEntity.getDepth())+pageEntity.getExpectScore();

			// set the score to the page entity
			pageEntity.setScore(score);
			
//			int temp = AStarCloseQueue.size();
//
//			System.out.println(temp);
			// set the parent index in the close queue
			//pageEntity.setParentIndex(parentLocation);

			// Add the page to ResultQueue
			ResultQueue.add(pageEntity);
			//System.out.println(pageEntity.toString());
			// Sort the result queue
			//ResultQueue.sort();

			// Judge the terminate or not
			if(Terminate.judge(score)&&Terminate.isStop()==false){
				AStarCloseQueue.add(pageEntity);
				Terminate.setStop(true);
			}



			//			System.out.println("URL is "+ pageEntity.getURL());
			//			System.out.println("score is "+ score);
			//			//System.out.println("Best Score is "+BestPage.getScore());
			//			System.out.println("Depth is " + pageEntity.getDepth());
			//			System.out.println("Herf is " +pageEntity.getHerf());
			//			System.out.println("ParentIndex is"+ pageEntity.getParentIndex());
			//			System.out.println("-------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

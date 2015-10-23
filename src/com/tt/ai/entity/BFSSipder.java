package com.tt.ai.entity;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tt.ai.handler.PreJudge;
import com.tt.ai.handler.ScoreCounter;
import com.tt.ai.handler.Terminate;
import com.tt.ai.thread.BFSTask;
import com.tt.ai.thread.ThreadsPool;

public class BFSSipder implements WebSpider{

	private String baseURL;
	private String keyWords;
	public BFSSipder(String baseURL, String keyWords) {
		this.baseURL = baseURL;
		this.keyWords = keyWords;
		RequestEntity.initRequestEntity(baseURL, keyWords);
	}

	public void getTarget(){
		try {
			work();
			for(int i=0;i<30;i++){
				Thread.sleep(1000);
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
		}
		if(BestPage.getScore()-BestPage.getExpectScore()==0){
			System.err.println("Sorry Can not Find Relevant Page, You can set work time longer.");
		}else{
			System.out.println("Target Page URL is: " + BestPage.getURL());
			System.out.println("Target Page Score is: " + BestPage.getScore());
			System.out.println("Target Page Depth is: " + BestPage.getDepth());
		}
		System.exit(0);

	}

	@Override
	public void getTarget(long timeout) {
		// TODO Auto-generated method stub
		if(timeout>10){
			try {
				work();
				for(int i=0;i < timeout;i++){
					Thread.sleep(1000);
				}
			}  catch (Exception e) {
				// TODO Auto-generated catch block
			}

			System.out.println("Target Page URL is: " + BestPage.getURL());
			System.out.println("Target Page Score is: " + BestPage.getScore());
			System.out.println("Target Page Depth is: " + BestPage.getDepth());
			System.exit(0);
		}else{
			System.out.println("Timeout please bigger than 10!");
		}
	}


	private void work() throws IOException, InterruptedException{

		double score = ScoreCounter.getScoreSetPage(baseURL,0);

		RequestEntity.setBaseScore(score);
		Terminate.setRate(score);
		PreJudge.setExpectTarget();

		ThreadsPool.StartWorkThread();

	}


}

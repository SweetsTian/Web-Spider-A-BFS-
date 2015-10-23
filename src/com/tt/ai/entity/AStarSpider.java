package com.tt.ai.entity;

import java.io.IOException;

import com.tt.ai.handler.PreJudge;
import com.tt.ai.handler.ScoreCounter;
import com.tt.ai.handler.Terminate;
import com.tt.ai.handler.WebPageHandler;
import com.tt.ai.thread.ThreadsPool;

public class AStarSpider implements WebSpider{

	private String baseURL;
	private String keyWords;
	private long runTime;
	private long startTime;
	private long timeout = 30;

	public AStarSpider(String baseURL, String keyWords) {
		super();
		this.baseURL = baseURL;
		this.keyWords = keyWords;
		RequestEntity.initRequestEntity(baseURL, keyWords);
	}


	@Override
	public void getTarget(){
		// TODO Auto-generated method stub

		startTime = System.currentTimeMillis();
		double score;
		try {
			score = ScoreCounter.getScore(baseURL,0);
			RequestEntity.setBaseScore(score);
			PageEntity pe = new PageEntity(baseURL, 0, null,0);
			pe.setScore(score);
			ResultQueue.add(pe);
			Terminate.setRate(score);
			PreJudge.setExpectTarget();

			ThreadsPool.StartWorkThread();
			work();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// show result

		ThreadsPool.destroy();
		PageEntity target = null;
		try{
		target = AStarCloseQueue.getHighest();
		}catch(Exception e){
			System.err.println("Sorry Can not Find Relevant Page, You can set work time longer.");
		}
		if(target.getScore()-target.getExpectScore()==0){
			System.err.println("Sorry Can not Find Relevant Page, You can set work time longer.");
		}else{

			System.out.println("Target Page URL is: " + target.getURL());
			System.out.println("Target Page Depth is: " + target.getDepth());
			System.out.println("Target Page Score is: " + target.getScore());
			System.out.println("------------------------- Route is: --------------------------------" );
			System.out.println(target.toString());
			int i = target.getParentIndex();
			while(i-1!=0){

				PageEntity p1 = AStarCloseQueue.get(i-1);
				i = p1.getParentIndex();
				System.out.println(p1.toString());
			}
			System.out.println(AStarCloseQueue.get(0));
		}
		System.exit(0);
	}

	@Override
	public void getTarget(long timeout) {
		// TODO Auto-generated method stub

		if(timeout>10){
			this.timeout = timeout;
			getTarget();
		}else{
			System.out.println("Timeout please bigger than 10!");
		}
	}

	private long getRunTime(){
		long endTime = System.currentTimeMillis();
		return(endTime - startTime);

	}



	private void work() throws InterruptedException{


		while(true){
			if(!ResultQueue.isEmpty()&&TaskQueue.isEmpty()){
				try{
					//ResultQueue.sort();
					PageEntity p = ResultQueue.getHighest();
					ResultQueue.remove(p);
					//System.out.println(p);
					AStarCloseQueue.add(p);
					int temp = AStarCloseQueue.size();
					WebPageHandler.addChildURLA(WebPageHandler.doc(p.getURL()), p.getDepth()+1,p.getScore(),temp);
					Thread.sleep(20);

				}catch(Exception e){

				}
			}else{
				Thread.sleep(50);
				if(Terminate.isStop()==true||getRunTime()>timeout*1000){

					break;
				}
			}

		}
	}

}



package com.tt.ai.handler;

import java.io.IOException;
import java.util.Map;

import com.tt.ai.entity.PageFrequency;
import com.tt.ai.entity.RequestEntity;

/**
 * The class provide the method to count score of a page frequency entity
 * @author Tian
 */
public class ScoreCounter {

	private static PageFrequency pageFrequency;

	/**
	 * Get page score and set the children page to task queue 
	 */
	public static double getScoreSetPage(String URL, int depth) throws IOException{
		pageFrequency = new PageFrequency(URL,depth);
		double score = scoreInBoldTag()+scoreInH1Tag()+scoreInH2Tag()+scoreInText()+scoreInTitle();
		pageFrequency.addChildPage(score);
		return score ;
	}
	
	/**
	 * Get page score
	 */
	public static double getScore(String URL, int depth) throws IOException{
		pageFrequency = new PageFrequency(URL,depth);
		double score = scoreInBoldTag()+scoreInH1Tag()+scoreInH2Tag()+scoreInText()+scoreInTitle();
		return score ;
	}
	
	/**
	 * Set the children page to task queue 
	 */
	public static void addChildPage(String URL, int depth,double Score) throws IOException{
		pageFrequency = new PageFrequency(URL,depth);
		WebPageHandler.addChildURL(WebPageHandler.doc(URL), depth,Score);
	}

	/**
	 * Get the score according to the items appearance in title, weight is 3.375
	 * @param title is the title text
	 * @return the title score
	 */
	private static double scoreInTitle(){
		double score = 0;
		int posCounter = 0;
		int negCounter = 0;
		Map<String, Integer> posFre=pageFrequency.getPosItemsFreInTitle();
		Map<String, Integer> negFre=pageFrequency.getNegItemsFreInTitle();
		if(RequestEntity.isPositiveItems()!=false){
			for(String key:posFre.keySet()){
				if(posFre.get(key)>0)
					posCounter++; // one item appearance once count++
			}
		}
		if(RequestEntity.isNegativeItems()!=false){
			for(String key:negFre.keySet()){
				if(negFre.get(key)>0)
					negCounter++; // one item appearance once count++
			}
		}
		score = posCounter*3.875-negCounter*3.875;
		return score;
	}

	/**
	 * Get the score according to the items appearance in title, weight is 2.5
	 * @param h1 is the h1 text list
	 * @return the h1 score
	 */
	private static double scoreInH1Tag(){
		double score = 0;
		int posCounter = 0;
		int negCounter = 0;
		Map<String, Integer> posFre=pageFrequency.getPosItemsFreInH1();
		Map<String, Integer> negFre=pageFrequency.getNegItemsFreInH1();
		if(RequestEntity.isPositiveItems()!=false){
			for(String key:posFre.keySet()){
				if(posFre.get(key)>0)
					posCounter++; // one item appearance once count++
			}
		}
		if(RequestEntity.isNegativeItems()!=false){
			for(String key:negFre.keySet()){
				if(negFre.get(key)>0)
					negCounter++; // one item appearance once count++
			}
		}
		score = posCounter*2.875-negCounter*2.875;
		//score = posCounter*2.875;
		return score;
	}

	/**
	 * Get the score according to the items appearance in title, weight is 1.75
	 * @param h2 is the h2 text list
	 * @return the h2 score
	 */
	private static double scoreInH2Tag(){
		double score = 0;
		int posCounter = 0;
		int negCounter = 0;
		Map<String, Integer> posFre=pageFrequency.getPosItemsFreInH2();
		Map<String, Integer> negFre=pageFrequency.getNegItemsFreInH2();
		if(RequestEntity.isPositiveItems()!=false){
			for(String key:posFre.keySet()){
				if(posFre.get(key)>0)
					posCounter++; // one item appearance once count++
			}
		}
		if(RequestEntity.isNegativeItems()!=false){
			for(String key:negFre.keySet()){
				if(negFre.get(key)>0)
					negCounter++; // one item appearance once count++
			}
		}
		score = posCounter*2.175-negCounter*3;
		//score = posCounter*2.175;
		return score;
	}

	/**
	 * Get the score according to the items appearance in title, weight is 1.375
	 * @param Bold is the Bold text list
	 * @return the Bold score
	 */
	private static double scoreInBoldTag(){
		double score = 0;
		int posCounter = 0;
		int negCounter = 0;
		Map<String, Integer> posFre = pageFrequency.getPosItemsFreInBold();
		Map<String, Integer> negFre = pageFrequency.getNegItemsFreInBold();
		if(RequestEntity.isNegativeItems()!=false){
			for(String key:negFre.keySet()){
				posCounter+=negFre.get(key); //count all items frequency
			}
		}
		if(RequestEntity.isPositiveItems()!=false){
			for(String key:posFre.keySet()){
				posCounter+=posFre.get(key); //count all items frequency
			}
		}



		score = 1.75*Math.log10(posCounter+1) - 1.75*Math.log10(negCounter+1); 
		//score = 1.75*Math.log10(posCounter+1);
		return score;
	}

	/**
	 * Get the score according to the items appearance in text
	 * @param content is the web page content
	 * @return the text score
	 */
	private static double scoreInText(){
		double score = 0;
		int posCounter = 0;
		int negCounter = 0;
		Map<String, Integer> posFre = pageFrequency.getPosItemsFreInContent();
		Map<String, Integer> negFre = pageFrequency.getNegItemsFreInContent();
		if(RequestEntity.isNegativeItems()!=false){
			for(String key:negFre.keySet()){
				posCounter+=negFre.get(key); //count all items frequency
			}
		}
		if(RequestEntity.isPositiveItems()!=false){
			for(String key:posFre.keySet()){
				posCounter+=posFre.get(key); //count all items frequency
			}
		}
		score = 1.25*Math.log10(posCounter+1) - 1.25*Math.log10(negCounter+1); 
		//score = 1.25*Math.log10(posCounter+1);
		return score;
	}

}

package com.tt.ai.handler;

import net.sourceforge.jFuzzyLogic.FIS;

import com.tt.ai.entity.RequestEntity;

/**
 * The class provide a method to determine when to terminate the search
 * @author Tian
 */
public class Terminate {
	private static boolean stop = false;
	private static double rate;
	private static double targetScore;



	//	public static double getRate() {
	//		return rate;
	//	}

	/**
	 * Use the jfuzzy logic to determine the rate
	 * Target page's score must bigger than rate*basescore(The score of the input page)
	 * @param baseScore is the score of the input page
	 * @author Tian
	 */
	public static void setRate(double baseScore){
		int citems = 0;
		if(RequestEntity.isNegativeItems()==true&&RequestEntity.isPositiveItems()==true){
			citems = RequestEntity.getPositiveTermsList().size() - RequestEntity.getNegativeTermsList().size();
			if(citems<0)
				citems = 0;
		}
		else if(RequestEntity.isNegativeItems()==false && RequestEntity.isPositiveItems()==true){
			citems = RequestEntity.getPositiveTermsList().size();
		}else if(RequestEntity.isNegativeItems()==true && RequestEntity.isPositiveItems()==false){

			citems = 0;
		}


		if(baseScore<0){
			baseScore = -baseScore;
		}

		if(baseScore==0){
			targetScore = 20;
		}
		else{
			FIS fis = FIS.load("conf/rules.fcl", true); //Load and parse the FCL
			fis.setVariable("basescore", baseScore); //Apply a value to a variable
			fis.setVariable("citems", citems);
			fis.evaluate(); //Execute the fuzzy inference engine
			
//			System.out.println("BBBBB: " + baseScore);
			rate = fis.getVariable("rate").getValue();
//			System.out.println("RRRRrrate:" + rate);
			targetScore = rate*baseScore;
		}
		//System.out.println(targetScore);
	}


	/**
	 * If find the target page then stop is true 
	 */
	public static boolean isStop() {
		return stop;
	}

	/**
	 * If find the target page then set stop as true 
	 */
	public static void setStop(boolean stops) {
		stop = stops;
	}

	/**
	 * Judge the page is target page or not  
	 * @param score is the page's score
	 * @return If score bigger than the value of baseScore*rate then return true else return false 
	 */
	public static boolean judge(double score){
		boolean result = false;
		if(targetScore < score){
			result = true;
		}
		return result;
	}

}

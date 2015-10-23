package com.tt.ai.handler;

import com.tt.ai.entity.RequestEntity;

import net.sourceforge.jFuzzyLogic.FIS;

/**
 * The class provide a method to count the expect score of the page by using jFuzzyLogic
 * @author Tian
 */
public class PreJudge {
	private static double expectTarget = 0;
	
	/**
	 * Get the expect score of a page
	 * @param ps is the parent page division the quantity of children page
	 * @param herf is the frequency of the key words appearance in the herf text
	 * @return the expect score
	 */
	public static double getScore(double ps,double herf){
		FIS fis = FIS.load("conf/rules.fcl", true); //Load and parse the FCL
		fis.setVariable("ps", ps); //Apply a value to a variable
		fis.setVariable("herf", herf);
		fis.evaluate(); //Execute the fuzzy inference engine
		double score = fis.getVariable("result").getValue();
		return score;
	}
	
	public static double getExpectTarget(){
		return expectTarget;
	}
	
	/**
	 * Set the target expect score according to the base score by jfuzzy logic
	 */
	public static void setExpectTarget(){
		double basescore = RequestEntity.getBaseScore();
		if(basescore > 0){
			FIS fis = FIS.load("conf/rules.fcl", true); //Load and parse the FCL
			fis.setVariable("basescore", basescore); //Apply a value to a variable
			fis.evaluate(); //Execute the fuzzy inference engine
			expectTarget = fis.getVariable("expectarget").getValue();
			
		}
		//System.out.println(expectTarget);
	}


}

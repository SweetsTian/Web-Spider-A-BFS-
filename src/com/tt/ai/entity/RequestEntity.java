package com.tt.ai.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tt.ai.handler.WordHandler;

/**
 * RequestEntity class to store the basic information about the search
 * @author Tian 
 */
public class RequestEntity {
	private static String BaseURL; // The URL user input base URL
	private static double BaseScore; // The Base page score
	private static String KeyWords; // Keywords user input
	private static List<String> PositiveTermsList = new ArrayList<String>(); //Positive Terms list
	private static List<String> NegativeTermsList = new ArrayList<String>(); // Negative terms list
	private static boolean positiveItems = true;  // if have positive items is true
	private static boolean negativeItems = true;  // if have negative items is true
	
	/**
	 * To initial the Request information And split the keywords to positive items and negative items
	 * @param baseURL is the input url
	 * @param keyWords is the input keywords 
	 */
	public static void initRequestEntity(String baseURL, String keyWords) {
		BaseURL = baseURL;
		KeyWords = keyWords;
		WordHandler.splitByNagWords(keyWords);

	}
	
	public static boolean isPositiveItems() {
		return positiveItems;
	}

	public static boolean isNegativeItems() {
		return negativeItems;
	}

	public static List<String> getPositiveTermsList() {
		return PositiveTermsList;
	}

	public static List<String> getNegativeTermsList() {
		return NegativeTermsList;
	}

	public static void setBaseScore(double baseScore){
		BaseScore = baseScore;
	}
	
	public static double getBaseScore(){
		return BaseScore;
	}
	
	public static void setPositiveItems(boolean positiveItems) {
		RequestEntity.positiveItems = positiveItems;
	}

	public static void setNegativeItems(boolean negativeItems) {
		RequestEntity.negativeItems = negativeItems;
	}

	public static void setPositiveTermsList(List<String> positiveTermsList) {
		PositiveTermsList = positiveTermsList;
	}

	public static void setNegativeTermsList(List<String> negativeTermsList) {
		NegativeTermsList = negativeTermsList;
	}

}

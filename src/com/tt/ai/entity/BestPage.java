package com.tt.ai.entity;

public class BestPage {
	
	private static String URL;
	private static double score = 0;
	private static int depth;
	private static double expectScore = 0;
	
	public static double getExpectScore() {
		return expectScore;
	}
	public static void setExpectScore(double expectScore) {
		BestPage.expectScore = expectScore;
	}
	public static String getURL() {
		return URL;
	}
	public static void setURL(String uRL) {
		URL = uRL;
	}
	public static double getScore() {
		return score;
	}
	public static void setScore(double scores) {
		score = scores;
	}
	public static int getDepth() {
		return depth;
	}
	public static void setDepth(int depths) {
		depth = depths;
	}
	
	

}

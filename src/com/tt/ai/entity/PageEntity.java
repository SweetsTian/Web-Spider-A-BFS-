package com.tt.ai.entity;

/**
 * PageEntity is the node of search
 * @author Tian 
 */
public class PageEntity {
	private String URL; //url of the page
	private int Depth; // depth of between the base page
	private double score; // the score of the page
	private String Herf; // the linked name of the page
	private int parentIndex = 0; // to marked the parent location in the AStarCloseQueue default is -1, the start page
	private double expectScore; // expectScore marked by the jfuzzylogic
	
	public PageEntity(String uRL,int depth,String herf,double expectScore) {
		URL = uRL;
		Depth = depth;
		Herf = herf;
		this.expectScore = expectScore;
	}

	
	public double getExpectScore() {
		return expectScore;
	}

	public void setExpectScore(double expectScore) {
		this.expectScore = expectScore;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}


	public String getURL() {
		return URL;
	}

	public String getHerf() {
		return Herf;
	}

	public void setHerf(String herf) {
		Herf = herf;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public void setDepth(int depth){
		Depth = depth;
	}
	
	public int getDepth(){
		return Depth;
	}

	@Override
	public String toString() {
		return "PageEntity [URL=" + URL + ", Depth=" + Depth + ", score="
				+ score + ", Herf=" + Herf + ", parentIndex=" + parentIndex
				+ ", expectScore=" + expectScore + "]";
	}

	public String getCompareScore() {
		// TODO Auto-generated method stub
		return String.valueOf(score);
	}

	
	
	

}

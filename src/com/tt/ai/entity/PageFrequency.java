package com.tt.ai.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;

import com.tt.ai.handler.FrequencyCounter;
import com.tt.ai.handler.WebPageHandler;


/**
 * The class to record the frequency of each item appearance in different part of the page 
 * The positive items and negative items' frequency
 * @author Tian
 */
public class PageFrequency {
	private String URL;
	private int Depth;
	private Document doc;
	private Map<String,Integer> posItemsFreInTitle = new HashMap<String,Integer>();
	private Map<String,Integer> posItemsFreInH1 = new HashMap<String,Integer>(); // the words and the frequency(int)
	private Map<String,Integer> posItemsFreInH2 = new HashMap<String,Integer>();
	private Map<String,Integer> posItemsFreInBold = new HashMap<String,Integer>();
	private Map<String,Integer> posItemsFreInContent = new HashMap<String,Integer>();
	
	private Map<String,Integer> negItemsFreInTitle = new HashMap<String,Integer>();
	private Map<String,Integer> negItemsFreInH1 = new HashMap<String,Integer>(); // the words and the frequency(int)
	private Map<String,Integer> negItemsFreInH2 = new HashMap<String,Integer>();
	private Map<String,Integer> negItemsFreInBold = new HashMap<String,Integer>();
	private Map<String,Integer> negItemsFreInContent = new HashMap<String,Integer>();
	
	public PageFrequency(String URL,int depth) throws IOException {
		this.URL = URL;
		Depth = depth;
		doc = WebPageHandler.doc(URL);
		initData();
	}
	
	/**
	 * To initial the frequency  
	 * @return If success return true, else return false
	 */
	private boolean initData() throws IOException{
	
		List<String> title = WebPageHandler.getTitle(doc);
		String content = WebPageHandler.getContent(doc);
		List<String> h1 = WebPageHandler.getFeatureText(doc, Feature.h1);
		List<String> h2 = WebPageHandler.getFeatureText(doc, Feature.h2);
		List<String> Bold = WebPageHandler.getFeatureText(doc, Feature.bold);
		if(RequestEntity.isNegativeItems()==false){
			negItemsFreInTitle = null;
			negItemsFreInContent = null;
			negItemsFreInBold = null;
			negItemsFreInH1 = null;
			negItemsFreInH2 = null;
		}else{
			negItemsFreInTitle = FrequencyCounter.getFreInList(title,false);
			negItemsFreInContent = FrequencyCounter.getFreInText(content,false);
			negItemsFreInBold = FrequencyCounter.getFreInList(Bold,false);
			negItemsFreInH1 = FrequencyCounter.getFreInList(h1,false);
			negItemsFreInH2 = FrequencyCounter.getFreInList(h2,false);
		}
		if(RequestEntity.isPositiveItems()==false){
			posItemsFreInTitle = null;
			posItemsFreInContent = null;
			posItemsFreInBold = null;
			posItemsFreInH1 = null;
			posItemsFreInH2 = null;
		}
		posItemsFreInTitle = FrequencyCounter.getFreInList(title,true);
		posItemsFreInContent = FrequencyCounter.getFreInText(content,true);
		posItemsFreInBold = FrequencyCounter.getFreInList(Bold,true);
		posItemsFreInH1 = FrequencyCounter.getFreInList(h1,true);
		posItemsFreInH2 = FrequencyCounter.getFreInList(h2,true);
		
		return true;
	}
	
	/**
	 * Add the children page of the page 
	 * @param expectedScore is the parent score of the page
	 */
	public void addChildPage(double expectedScore) throws IOException{
		WebPageHandler.addChildURL(doc, Depth+1,expectedScore);
	}


	public Map<String, Integer> getPosItemsFreInTitle() {
		return posItemsFreInTitle;
	}



	public Map<String, Integer> getPosItemsFreInH1() {
		return posItemsFreInH1;
	}


	public Map<String, Integer> getPosItemsFreInH2() {
		return posItemsFreInH2;
	}


	public Map<String, Integer> getPosItemsFreInBold() {
		return posItemsFreInBold;
	}


	public Map<String, Integer> getPosItemsFreInContent() {
		return posItemsFreInContent;
	}


	public Map<String, Integer> getNegItemsFreInTitle() {
		return negItemsFreInTitle;
	}


	public Map<String, Integer> getNegItemsFreInH1() {
		return negItemsFreInH1;
	}

	public void setNegItemsFreInH1(Map<String, Integer> negItemsFreInH1) {
		this.negItemsFreInH1 = negItemsFreInH1;
	}

	public Map<String, Integer> getNegItemsFreInH2() {
		return negItemsFreInH2;
	}


	public Map<String, Integer> getNegItemsFreInBold() {
		return negItemsFreInBold;
	}


	public Map<String, Integer> getNegItemsFreInContent() {
		return negItemsFreInContent;
	}



}

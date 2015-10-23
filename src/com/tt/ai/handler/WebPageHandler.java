package com.tt.ai.handler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tt.ai.entity.ResultQueue;
import com.tt.ai.entity.Feature;
import com.tt.ai.entity.TaskQueue;
import com.tt.ai.entity.ResultQueue;
import com.tt.ai.thread.AStarTask;
import com.tt.ai.thread.BFSTask;
import com.tt.ai.thread.ThreadsPool;

/**
 * The WebPageHandler method provide a set of method to deal with a page
 * @author Tian
 */
public class WebPageHandler {

	/**
	 * Get a web page doc(Jsoup) 
	 */
	public static Document doc(String URL) throws IOException {
		try{
			Document doc = Jsoup.connect(URL).get();
			return doc;
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * Get title of the web page
	 * @param URL is the page URL
	 * @return the title result
	 */
	public static List<String> getTitle(Document doc) throws IOException{
		try{
			String title = doc.title();
			return WordHandler.stringHandler(title);
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * Add all URL to the task Queue  the BFS algorithm use it
	 */
	public static void addChildURL(Document doc,int depth,double score) throws IOException {
		try {
			Elements hrefs = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");
			Elements links = hrefs.select("[href^=http]");
			double ps = score/links.size(); 
			for (Element link : links) {
				String linkHref = link.attr("href");
				String herf = link.text();
				addLinkB(linkHref, herf, depth, ps);

			}

			for (Element src : media) {
				String link = src.attr("abs:src");
				addLinkB(link, null, depth, ps);
			}

			for (Element link : imports) {
				String linkherf = link.attr("abs:href");
				addLinkB(linkherf, null, depth, ps);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Add all URL to the task Queue  the A star algorithm use it
	 * @param score is the parent page's score
	 */
	public static void addChildURLA(Document doc,int depth, double score, int parentLocation) throws IOException {
		try {
			Elements hrefs = doc.select("a[href]");
			Elements media = doc.select("[src]");
			Elements imports = doc.select("link[href]");
			Elements links = hrefs.select("[href^=http]");
			double ps = score/links.size(); 
			for (Element link : links) {
				String linkHref = link.attr("href");
				String herf = link.text();
				addLinkA(linkHref, herf, depth, parentLocation, ps);
				//System.out.println(linkHref);

			}

			for (Element src : media) {
				String link = src.attr("abs:src");
				addLinkA(link, null, depth, parentLocation, ps);
			}

			for (Element link : imports) {
				String linkherf = link.attr("abs:href");
				addLinkA(linkherf, null, depth, parentLocation, ps);
			}
		} catch (Exception e) {

		}
	}

	private static void addLinkA(String link,String herf,int depth,int parentLocation,double ps){
		if(ResultQueue.match(link)==false){ // judge the page is not in the result queue
			//String herf = link.text();
			int counter = 0;
			if(herf != null){
				herf = herf.toLowerCase();
				
				List<String> herfs = WordHandler.stringHandler(herf);
				Map<String,Integer> pTemp = FrequencyCounter.getFreInList(herfs, true);
				for(String key:pTemp.keySet()){
					if(pTemp.get(key)>0)
						counter++;
				}
			}else{
			}
			

			

			double expectScore = PreJudge.getScore(ps, counter);

			if(expectScore>=PreJudge.getExpectTarget()){
				AStarTask task = new AStarTask(link, depth, herf ,expectScore, parentLocation);
				TaskQueue.execute(task);
			}
		} // if not in the queue add it to the task queue
		else{
		}
	}
	
	private static void addLinkB(String link,String herf,int depth,double ps){
		if(ResultQueue.match(link)==false){ // judge the page is not in the result queue
			//String herf = link.text();
			int counter = 0;
			if(herf != null){
				herf = herf.toLowerCase();
				
				List<String> herfs = WordHandler.stringHandler(herf);
				Map<String,Integer> pTemp = FrequencyCounter.getFreInList(herfs, true);
				for(String key:pTemp.keySet()){
					if(pTemp.get(key)>0)
						counter++;
				}
			}else{
			}
			

			

			double expectScore = PreJudge.getScore(ps, counter);

			if(expectScore>=PreJudge.getExpectTarget()){
				BFSTask task = new BFSTask(link, depth, herf ,expectScore);
				TaskQueue.execute(task);
			}
		} // if not in the queue add it to the task queue
		else{
		}
	}


	/**
	 * Get feature text in web page like bold h1...
	 * @param URL is the URL of the web page
	 * @return The feature text
	 */
	public static List<String> getFeatureText(Document doc,Feature feature) throws IOException{
		List<String> tempList = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		try{
			Element body = doc.body();
			Elements feas = body.select(feature.value());
			for(Element text:feas){
				String temp = text.text().toLowerCase();
				tempList = WordHandler.stringHandler(temp);
				result.addAll(tempList);
			}
			return result;
		}catch(Exception e){
			return null;
		}
	}

	/**
	 * Get content of the web page
	 * @param URL is the page URL
	 * @return the content result
	 */
	public static String getContent(Document doc) throws IOException{

		try{
			Element body = doc.body();
			return body.text();
		}catch(Exception e){
			return null;
		}
	}
}

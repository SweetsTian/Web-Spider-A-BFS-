package com.tt.ai.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tt.ai.entity.RequestEntity;


/**
 * A class to provide method to count the frequency in list or string 
 * @author Tian
 */
public class FrequencyCounter {

	/**
	 * To count the frequency of each key word appearance in a text
	 * The key words list is read from the pageEntity  class 
	 * @param content text
	 * @param flag to show the positive or negative words, true is positive words and false is negative words
	 * @return the keywords and the frequency
	 */
	public static Map<String,Integer> getFreInText(String content,boolean flag){
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<String> keyWordsList = new ArrayList<String>();
		if(flag==true)
			keyWordsList = RequestEntity.getPositiveTermsList();
		else 
			keyWordsList = RequestEntity.getNegativeTermsList();
		//System.out.println("keywords list is: "+keyWordsList);
		for(String s:keyWordsList){
			int fre = freqInString(s, content);
			result.put(s, fre);
		}

		//System.out.println("freInString is "+ result);
		return result;
	}

	/**
	 * To count the frequency of each key word appearance in a string list
	 * The key words list is read from the pageEntity  class 
	 * @param the string list
	 * @param flag to show the positive or negative words
	 * @return the keywords and the frequency
	 */
	public static Map<String,Integer> getFreInList(List<String> lTarget,boolean flag){
		Map<String, Integer> result = new HashMap<String, Integer>();
		List<String> keyWordsList = new ArrayList<String>();
		if(flag==true)
			keyWordsList = RequestEntity.getPositiveTermsList();
		else 
			keyWordsList = RequestEntity.getNegativeTermsList();
		//System.out.println("keywords list is: "+keyWordsList);
		if(keyWordsList.equals(null))
			return null;
		for(String sCompare : keyWordsList){
			result.put(sCompare, freqInList(sCompare, lTarget));
		}
		
		//System.out.println("freInList is "+ result);
		//System.out.println("AAAAAAAa: "+result);
		return result;
	}

	/**
	 * Get the frequency of a string appearance in a String list
	 * @param sCompare is the string want to count
	 * @param lTarget is the string list
	 * @return the frequency of the string
	 */
	private static int freqInList(String sCompare,List<String> lTarget){
		try{
			int count = 0;
			for(String s:lTarget){
				if(sCompare.equals(s))
					count++;
			}
			return count;
		}catch(Exception e){
			return 0;
		}
	}

	/**
	 * Count the frequency of a string appearance in another string
	 * @param sCompare is the string need to compare
	 * @param sTarget is the string to compare and count the frequency
	 */
	private static int freqInString(String sCompare,String sTarget){
		try{
			int count = -1;
			int length = sCompare.length();
			int position = -length;
			while(position != -1){
				position = sTarget.indexOf(sCompare, position+length);
				//System.out.println(position);
				count++;
			}
			return count;
		}catch(Exception e){
			return 0;
		}
	}
}

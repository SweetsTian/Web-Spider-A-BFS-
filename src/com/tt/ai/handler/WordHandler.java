package com.tt.ai.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tt.ai.entity.RequestEntity;

/**
 * The method provide a set of method to handle the string and String list
 * @author Tian
 */
public class WordHandler {
	
	private static List<String> NegativeWords = new ArrayList<String>(){{
		add("not");
		add("no");
		add("non");
		add("neither");
		add("never");
		add("none");
		add("not");
	}};

	private static List<String> PositiveWords = new ArrayList<String>(){{
		add("but");
		add("rather");
	}};

 
	/**
	 * Split the a string to two lists the positive items and negative items 
	 * Store two list to request entity
	 * @author Tian
	 */
	public static void splitByNagWords(String s){
		List<String> positiveTerms = new ArrayList<String>();//to store the positive terms
		List<String> negativeTerms = new ArrayList<String>();//to store the negative terms 
		s= s.toLowerCase();
		s = removePunctuation(s);
		String[] temp = splitBySpace(s);

		int nag_Position = splitNegativePos(temp);
		if(nag_Position==0) // all words' position is after the first negative words
		{
			int pos_Position = splitPositivePos(temp);
			if(pos_Position==temp.length) // not positive words so  
			{
				negativeTerms = StrignArray2List(temp);
				RequestEntity.setPositiveItems(false);
				positiveTerms = null;
			}
			else // fore is negative terms and later is positive terms
			{
				for(int i = 0;i<pos_Position;i++){
					negativeTerms.add(temp[i]);
				}
				for(int i = pos_Position;i<temp.length;i++){
					positiveTerms.add(temp[i]);
				}
			}
		}
		else if(nag_Position==temp.length) // no negative words in the s
		{
			negativeTerms = null;
			positiveTerms = StrignArray2List(temp);
			RequestEntity.setNegativeItems(false);

		}else{ // fore is positive terms and later is negative terms
			for(int i = 0;i<nag_Position;i++){
				positiveTerms.add(temp[i]);
			}
			for(int i = nag_Position;i<temp.length;i++){
				negativeTerms.add(temp[i]);
			}

		}// end the word analysis
		
		negativeTerms = removeSame(negativeTerms);
		negativeTerms = removeStopwords(negativeTerms);
		positiveTerms = removeSame(positiveTerms);
		positiveTerms = removeStopwords(positiveTerms);
		
		RequestEntity.setNegativeTermsList(negativeTerms);
		RequestEntity.setPositiveTermsList(positiveTerms);
	}
	

	/**
	 * Get the first negative words position
	 * @return position
	 */
	private static int splitNegativePos(String[] sTarget){
		int miniPosition = sTarget.length;
		for(String s : NegativeWords){
			for(int i = 0 ; i<sTarget.length ; i++){
				if(sTarget[i].equals(s) && i<miniPosition)
					miniPosition = i;
			}
		}
		return miniPosition;
	}

	/**
	 * Get the first positive words position
	 * @return position
	 */
	private static int splitPositivePos(String[] sTarget){
		int miniPosition = sTarget.length;
		for(String s : PositiveWords){
			for(int i = 0 ; i<sTarget.length ; i++){
				if(sTarget[i].equals(s) && i<miniPosition)
					miniPosition = i;
			}
		}
		return miniPosition;
	}
	
	
	/**
	 * remove punctuation and split a string by space and remove same words stopwords
	 * @param the s want to deal with
	 * @return the result string list
	 */
	public static List<String> stringHandler(String s){
		s = s.toLowerCase();
		s = removePunctuation(s);
		String[] temp = splitBySpace(s);
		List<String> result = StrignArray2List(temp);
		result = removeSame(result);
		result = removeStopwords(result);
		return result;
	}

	/**
	 * split a string by space and return the string array
	 * @param s is the string need to split
	 * @return the string array in single word 
	 */
	private static String[] splitBySpace(String s){
		String[] result = s.split(" ");
		return result;
	}

	/**
	 * Use hash table to remove same words
	 * @param the string array
	 * @return the string array without same words
	 */
	private static List<String> removeSame(List<String> str){
		if(str==null){
			return null;
		}
		HashMap<String, Integer> rs = new HashMap<String,Integer>();
		List<String> list = new ArrayList<String>();
		for(String s:str){
			rs.put(s, 1);
		}
		for(String s:rs.keySet()){
			list.add(s);
		}
		//String[] arr = (String[])list.toArray(new String[list.size()]);
		return list;
	}


	/**
	 * Remove the stopwords 
	 * @return result
	 */
	private static List<String> removeStopwords(List<String> s){
		if(s==null){
			return null;
		}
		//List<String> s = StrignArray2List(str);
		List<String> temp = new ArrayList<String>();
		for(String word:s){
			if(Stopwords.compare(word)){
				temp.add(word);
			}
		}

		for(String delete:temp){
			s.remove(delete);
		}
		return s;
	}

	
	/**
	 * Remove punctuation and space
	 */
	private static String removePunctuation(String s ){
		s = s.trim();
		s = s.replaceAll("[\\pP\\p{Punct}]", "");

		Pattern p=Pattern.compile(" {2,}");
		Matcher m=p.matcher(s);
		String second=m.replaceAll(" ");
		
		return second;
	}

	/**
	 * Transfer string array to string list
	 * @return Result
	 */
	private static List<String> StrignArray2List(String[] s){
		List<String> result = new ArrayList<String>();
		for(int i = 0 ; i < s.length ; i++){
			result.add(s[i]);
		}
		return result;
	}



}

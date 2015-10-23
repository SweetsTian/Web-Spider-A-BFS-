package com.tt.ai.handler;


/**
 * The class provide method to judge the string is a integer or a double
 * @author Tian
 */
public class NumberHandler {

	/**
	 * Judge string is a Integer or not
	 * @param value is the string
	 * @return if is a Integer return true else return false
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	/**
	 * Judge string is a double or not
	 * @param value is the string
	 * @return if is a double return true else return false
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

/**
 * @author ibirc
 *
 */
public class YesNoInput {
	
	public static boolean stringToBoolean(String inValue) {
		boolean  answer = false;
		if(inValue.toLowerCase().contentEquals("yes")) {
			answer = true;
		} else {
			answer = false;
		}
		return answer;
	}
}

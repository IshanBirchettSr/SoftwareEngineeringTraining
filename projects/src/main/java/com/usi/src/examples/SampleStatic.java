/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package examples;

/**
 * @author ibirc
 *
 */
public class SampleStatic {
    static public String boxName = "UPS";
    public String boxAddress = "10 Main st.";

    /**
     * @return the boxName
     */
    public static String getBoxName() {

	return boxName;
    }

    /**
     * @param boxName the boxName to set
     */
    public static void setBoxName(String boxName) {
	SampleStatic.boxName = boxName;

    }

    /**
     * @return the boxAddress
     */
    public String getBoxAddress() {
	return boxAddress;
    }

    /**
     * @param boxAddress the boxAddress to set
     */
    public void setBoxAddress(String boxAddress) {
	this.boxAddress = boxAddress;
    }

    /**
     * 
     */
    public SampleStatic() {
	// TODO Auto-generated constructor stub
    }

}

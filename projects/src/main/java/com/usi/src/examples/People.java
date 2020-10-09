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
public class People extends Review {
    // public String name = "Pam";

    /**
     * 
     */
    public People() {

	System.out.printf("Name: %s\n", name);
	System.out.printf("Age: %d\n", age);
	System.out.printf("Base : %d\n", 0xA0);
	System.out.printf("Base : %d\n", 0110);
	System.out.printf("Base : %x\n", 0xF0);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	People p = new People();
	// System.out.printf("Name: %s", name1);

    }

}

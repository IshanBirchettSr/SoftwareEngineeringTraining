/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package customerservice;

import java.awt.Dimension;

/**
 * @author ibirc
 *
 */
public class Shelve {
    Dimension[] slots;

    /*
     * Constructor
     * 
     * @param int numberSlots
     * 
     * @param int width
     * 
     * @param int height
     */
    public Shelve(int numberSlots, int width, int height) {
	for (int i = 0; i < numberSlots; i++) {
	    slots[i] = new Dimension(width, height);
	}
    }
}

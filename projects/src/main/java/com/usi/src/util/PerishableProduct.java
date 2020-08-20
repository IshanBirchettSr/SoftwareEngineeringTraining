/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

//import java.util.Date;

/**
 * @author ibirc
 *
 */
public class PerishableProduct extends Product {

    /**
     * Data Members
     */
    // private Date experationDate;

    public PerishableProduct() {

    }

    @Override
    public boolean recordToProduct(String record) {

	return false;
    }
}

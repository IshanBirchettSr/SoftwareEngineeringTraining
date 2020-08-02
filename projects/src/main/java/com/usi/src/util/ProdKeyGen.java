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
public class ProdKeyGen {
    static String key = null;

    // Example of Polymorphism - "is-a" relationships on Product.
    public static String genKey(Product inProd) {
	key = null;
	if (inProd != null) {
	    key = String.format(StoreConstants.PROD_KEY_FORMATTER, inProd.getProductName(), inProd.getBarCode());
	}
	return key;
    }

}

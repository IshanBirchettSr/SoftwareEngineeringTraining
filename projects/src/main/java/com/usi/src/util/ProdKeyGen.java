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

    public static String genKey(Product inProd) {
	if (inProd != null) {
	    key = String.format(StoreConstants.PROD_KEY_FORMATTER, inProd.getDeptNameEnum(), inProd.getBandName(),
		    inProd.getProductName(), inProd.getWeightEnum(), inProd.getColorEnum());
	}
	return key;
    }

}

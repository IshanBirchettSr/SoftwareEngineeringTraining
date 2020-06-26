/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import util.StoreConstants.deptNames;

/**
 * @author ibirc
 *
 */
public abstract class Product {

    /**
     * data members
     * 
     * @return
     */
    private String productName;
    private deptNames deptEnum;

    public abstract boolean loadProducts();

}

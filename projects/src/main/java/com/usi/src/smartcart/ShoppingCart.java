/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package smartcart;

import java.util.ArrayList;
import java.util.List;

import util.Product;

/**
 * @author ibirc and Allma M. Johnson
 *
 */
public class ShoppingCart {

    private List<Product> scProds;
    private double runningTotal = 0.0;

    /**
     * 
     */
    public ShoppingCart() {
	scProds = new ArrayList<Product>();
    }

    public void addProduct(Product storeProduct) {
	scProds.add(storeProduct);
	setRunningTotal(storeProduct.getPrice());

    }

    public List<Product> getProductList() {
	return scProds;

    }

    /**
     * @return the runningTotal
     */
    public double getRunningTotal() {
	return runningTotal;
    }

    /**
     * @param runningTotal the runningTotal to set
     */
    public void setRunningTotal(double runningTotal) {
	this.runningTotal += runningTotal;
    }

    public void deductFromRunningTotal(double runningTotal) {
	this.runningTotal += runningTotal;
    }

    public void returnProductToShelf(Product returnProduct) {
	scProds.remove(returnProduct);
	deductFromRunningTotal(returnProduct.getPrice());

    }

    public void whatsInCart() {
	int sz = scProds.size();
	for (int i = 0; i < sz; i++) {
	    System.out.printf("%d %s \n", i, scProds.get(i).getProductName());

	}

    }
}

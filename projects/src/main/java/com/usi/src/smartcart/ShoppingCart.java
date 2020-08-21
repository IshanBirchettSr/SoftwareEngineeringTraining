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

    /**
     * 
     */
    public ShoppingCart() {
	scProds = new ArrayList<Product>();
    }

    public void addProduct(Product storeProduct) {
	scProds.add(storeProduct);
    }

    public List<Product> getProductList() {

	scProds.sort((Product p1, Product p2) -> p1.getBrandName().compareTo(p2.getBrandName()));
	return scProds;
    }

    /**
     * @return the runningTotal
     */
    public double getRunningTotal() {
	double runningTotal = 0;
	for (Product pd : scProds) {
	    runningTotal += pd.getPrice();
	}
	return runningTotal;
    }

    public void returnProductToShelf(Product returnProduct, int qnty) {
	for (int i = 0; i < qnty; i++) {
	    scProds.remove(returnProduct);
	}
    }

    public void whatsInCart() {
	int sz = scProds.size();
	for (int i = 0; i < sz; i++) {
	    System.out.printf("%d %s \n", i, scProds.get(i).getProductName());

	}

    }
}

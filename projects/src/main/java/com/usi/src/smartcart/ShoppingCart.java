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
import util.StoreConstants;

/**
 * @author ibirc and Allma M. Johnson
 *
 */
public class ShoppingCart {

    private List<Product> scProds;
    private double aarpDiscount = 0.00;
    private double membershipDiscount = 0.0;
    private double taxesTotal = 0.0;

    private double grandTotal = 0.0;
    private double subTotal = 0.0;

    /**
     * @return the grandTotal
     */
    public double getGrandTotal() {
	double savings = (getAarpDiscount() + getMembershipDiscount());
	grandTotal = ((getRunningTotal() - savings) + getTaxesTotal());
	return grandTotal;
    }

    /**
     * @param grandTotal the grandTotal to set
     */
    public void setGrandTotal(double grandTotal) {
	this.grandTotal = grandTotal;
    }

    /**
     * @return the aarpDiscount
     */
    public double getAarpDiscount() {
	aarpDiscount = (getRunningTotal() * StoreConstants.AARP_DISCOUNT);
	return aarpDiscount;
    }

    /**
     * @return the membershipDiscount
     */
    public double getMembershipDiscount() {
	membershipDiscount = (getRunningTotal() * StoreConstants.TODAYS_MEMBER_DISCOUNT);
	return membershipDiscount;
    }

    /**
     * @return the taxesTotal
     */
    public double getTaxesTotal() {
	double savings = (getAarpDiscount() + getMembershipDiscount());
	taxesTotal = ((getRunningTotal() - savings) * StoreConstants.STATE_SALES_TAX);
	return taxesTotal;
    }

    /**
     * 
     */
    public ShoppingCart() {
	scProds = new ArrayList<Product>();
    }

    public void addProduct(Product storeProduct) {
	scProds.add(storeProduct);
    }

    /**
     * @return the getSubTotal
     */
    public double getSubTotal() {
	double savings = (getAarpDiscount() + getMembershipDiscount());
	subTotal = getRunningTotal() - savings;
	return subTotal;
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

    /**
     * @return
     */
    public double getTotalSavings() {
	double savings = (getAarpDiscount() + getMembershipDiscount());
	return savings;
    }
}

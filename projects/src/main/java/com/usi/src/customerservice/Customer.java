/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package customerservice;

import java.util.List;

import smartcart.ShoppingCart;
import util.Department;
import util.Product;

/**
 * @author malac
 *
 */
public class Customer {
    ShoppingCart cart = null;
    List<Department> dList = null;

    /**
     * @return
     * 
     */
    public Customer() {
	dList = SuperStore.getdList();
	cart = new ShoppingCart();
    }

    // Add Products
    public void addProduct(Product pd, int qnty) {
	for (int i = 0; i < qnty; i++) {
	    cart.addProduct(pd);
	}
    }

    public double cartTotal() {
	return cart.getRunningTotal();
    }

    /**
     * @return
     */
    public List<Product> getListOfProds() {

	List<Product> cpList = cart.getProductList();
	return cpList;
    }

}

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
    // data member section
    // type name, object name, =, initialize
    ShoppingCart cart = null;
    List<Department> dList = null;
    List<Product> pList = null;

    /**
     * @return
     * 
     */
    public void Customer() {
	cart = new ShoppingCart();
    }

    public void startShopping() {
	for (Department dp : dList) {
	    dp.listProducts();
	    // decide which products
	    // add product to cart.

	}

    }
    // Testing

    /**
     * @return
     */
    public List<Product> getListOfProds() {
	// TODO Auto-generated method stub
	// call getProductList in shoppingCart
	List<Product> cpList = cart.getProductList();

	// List<Product> pList = sc.getProductList();
	// Remember to change null to the name of shopping chart
	return cpList;
    }

    public void setDepartment(List<Department> dps) {
	dList = dps;

    }

}

/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package smartcart;

import java.util.List;

import customerservice.Customer;
import util.Product;

/**
 * @author Allma M. Johnson
 *
 */
public class StoreCheckOut extends Receipt {

    /**
     * @param prodCheckout
     */
    public StoreCheckOut() {

	// TODO Auto-generated constructor stub
    }

    public boolean checkoutCustomer(Customer cust) {
	// Get list of productions
	List<Product> custProdList = cust.getListOfProds();
	// send to print
	setProdList(custProdList);
	return true;
    }
}

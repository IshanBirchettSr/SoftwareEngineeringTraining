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
//    List<Product> pList = null;

    /**
     * @return
     * 
     */
    public Customer() {
	dList = SuperStore.getdList();
	cart = new ShoppingCart();
    }

//    public void startShopping() {
//	int pdNumber = -1;
//	int qNumber = -1;
//	Scanner in = new Scanner(System.in);
//	cart = new ShoppingCart();
//	dList = SuperStore.getdList();
//	for (Department dp : dList) {
//	    System.out.printf("Department: %s\n", dp.getDeptName());
//	    dp.listProducts();
//	    do {
//		System.out.printf("Please enter product #:");
//		pdNumber = in.nextInt();
//		if (pdNumber == 0) {
//		    break;
//		}
//		System.out.printf("Please enter quantity:");
//		qNumber = in.nextInt();
//		pList = dp.getProds(pdNumber, qNumber);
//		for (Product pd : pList) {
//		    cart.addProduct(pd);
//		}
//	    } while (pdNumber != 0);
//	}
//    }

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
	// TODO Auto-generated method stub
	// call getProductList in shoppingCart
	List<Product> cpList = cart.getProductList();

	// List<Product> pList = sc.getProductList();
	// Remember to change null to the name of shopping chart
	return cpList;
    }

}

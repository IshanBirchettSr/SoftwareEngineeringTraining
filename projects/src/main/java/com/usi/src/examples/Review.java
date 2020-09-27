/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package examples;

import java.util.List;

import javafx.scene.Scene;
import util.Department;
import util.Product;

/**
 * @author ibirc
 *
 */
public class Review extends Department {

    String name = null;

    /**
     * 
     */
    public Review() {
	// TODO Auto-generated constructor stub
    }

    private void run() {
	// Do this first
	// do this second
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	Review app = new Review();
	app.run();

    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub

    }

    @Override
    public void listProducts() {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Product> getProducts() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Product> getProds(int index, int quantity) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Scene getScene() {
	// TODO Auto-generated method stub
	return null;
    }

}

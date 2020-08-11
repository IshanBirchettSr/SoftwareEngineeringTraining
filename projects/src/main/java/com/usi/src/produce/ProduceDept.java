/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package produce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.scene.Scene;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author ibirc
 *
 */
public class ProduceDept extends Department {
    String deptName = StoreConstants.deptNames.PRODUCE.name();
    List<String> produceRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold ProduceProd objects.
    HashMap<String, ProduceProd> ProduceProducts;

    /**
     * Constructor
     */
    public ProduceDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PRODUCE_TRUCK);
	produceRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(produceRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	ProduceProducts = new HashMap<String, ProduceProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : produceRecords) {
	    ProduceProd pdd = new ProduceProd();
	    boolean recordToProductSuccessful = pdd.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to produceProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pdd);

		int howMany = pdd.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    ProduceProducts.put(prodKey + 1, pdd);
		}

	    }

	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    produceRecords.size(), ProduceProducts.size());
	}

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> produceProductKeys = ProduceProducts.keySet();

	int totalProducts = produceProductKeys.size();
	int i = 1;
	for (String pKey : produceProductKeys) {
	    Product pd = ProduceProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int qauntity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < qauntity; i++) {
	    ProduceProd pd = ProduceProducts.get(pKey);
	    pdList.add(pd);
	}

	return pdList;
    }

    @Override
    public List<Product> getProducts() {
	List<Product> pList = null;

	return pList;
    }

    @Override
    public Scene getScene() {
	// TODO Auto-generated method stub
	return null;
    }
}

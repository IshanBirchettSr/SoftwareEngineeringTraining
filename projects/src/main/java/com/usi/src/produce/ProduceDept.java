/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package produce;

import java.util.HashMap;
import java.util.List;

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
    // HashMap<K, V> to hold ProduceProd objects.
    HashMap<String, ProduceProd> ProduceProducts;

    /**
     * Constructor
     */
    public ProduceDept() {
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.PRODUCE_TRUCK);
	produceRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(produceRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// produceRecords.size());

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

	    // If it fails to convert any field, don't add that object to autoProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(pdd);
		ProduceProducts.put(prodKey, pdd);
	    }
	}
	System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		produceRecords.size(), ProduceProducts.size());

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
}

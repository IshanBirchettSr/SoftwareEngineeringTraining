/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package toiletries;

import java.util.HashMap;
import java.util.List;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author chich
 *
 */
public class ToiletryDept extends Department {

	String deptName = StoreConstants.deptNames.TOILETRIES.name();
	List<String> toletriesRecords = null;
// HashMap<K, V> to hold ElectronicsProd objects.
	HashMap<String, ToiletryProd> ToiletryProducts;

	/**
	 * Constructor
	 */
	public ToiletryDept() {
// Record Load
		DataCsvLoad unLoadTrucks = new DataCsvLoad();
		unLoadTrucks.loadData(StoreConstants.TOILETRIES_TRUCK);
		toletriesRecords = unLoadTrucks.getRecords();
		this.setLoadedRecords(toletriesRecords);
// System.out.printf("%s Department open with %d records\n", deptName,
// autoRecords.size());

// Automotive Product Load
		ToiletryProducts = new HashMap<String, ToiletryProd>();
		loadProducts();
	}

	@Override
	protected void loadProducts() {
// Load products
		for (String record : toletriesRecords) {
			ToiletryProd tp = new ToiletryProd();
			boolean recordToProductSuccessful = tp.recordToProduct(record);

			// If it fails to convert any field, don't add that object to autoProducts
			if (recordToProductSuccessful == true) {
				String prodKey = ProdKeyGen.genKey(tp);
				ToiletryProducts.put(prodKey, tp);
			}
		}
		System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
				toletriesRecords.size(), ToiletryProducts.size());

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

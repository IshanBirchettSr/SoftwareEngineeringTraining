/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package seafood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import util.DataCsvLoad;
import util.Department;
import util.ProdKeyGen;
import util.Product;
import util.StoreConstants;

/**
 * @author Allma M. Johnson
 *
 */
public class SeafoodDept extends Department {
    String deptName = StoreConstants.deptNames.SEAFOOD.name();
    List<String> seafoodRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold SeafoodProd objects.
    HashMap<String, SeafoodProd> seafoodProducts;

    /**
     * Constructor
     */
    public SeafoodDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.SEAFOOD_TRUCK);
	seafoodRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(seafoodRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Automotive Product Load
	seafoodProducts = new HashMap<String, SeafoodProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : seafoodRecords) {
	    SeafoodProd sfp = new SeafoodProd();
	    boolean recordToProductSuccessful = sfp.recordToProduct(record);

	    // If it fails to convert any field, don't add that object to seafoodProducts
	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(sfp);

		int howMany = sfp.getNumUnitsInstock();
		for (int i = 0; i < howMany; i++) {

		    seafoodProducts.put(prodKey + 1, sfp);
		}

	    }

	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    seafoodRecords.size(), seafoodProducts.size());

	}

    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> seafoodProductKeys = seafoodProducts.keySet();

	int totalProducts = seafoodProductKeys.size();
	int i = 1;
	for (String pKey : seafoodProductKeys) {
	    Product pd = seafoodProducts.get(pKey);
	    if (aKey != pKey) {
		System.out.printf("%d: %s %s\t%.2f\n", i, pd.getBrandName(), pd.getProductName(), pd.getPrice());
	    }
	    aKey = pKey;
	    keyMap.put(i, pKey);
	    i++;
	}
    }

    public List<Product> getProds(int index, int quantity) {
	ArrayList<Product> pdList = new ArrayList<Product>();
	String pKey = keyMap.get(index);
	for (int i = 0; i < quantity; i++) {
	    SeafoodProd pd = seafoodProducts.get(pKey);
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
	Image seafoodImage = new Image(StoreConstants.SEAFOODDEPT);
	ImageView iv = new ImageView();
	iv.setImage(seafoodImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);

	HBox seafoodView = new HBox(iv);
	seafoodView.setAlignment(Pos.CENTER_LEFT);
	Scene seafoodScene = new Scene(seafoodView, 600, 575);
	return seafoodScene;
    }
}

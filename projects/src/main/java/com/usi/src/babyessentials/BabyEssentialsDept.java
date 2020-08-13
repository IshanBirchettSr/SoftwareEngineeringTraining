/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package babyessentials;

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
 * @author chich
 *
 */
public class BabyEssentialsDept extends Department {
    String deptName = StoreConstants.deptNames.BABY_ESSENTIALS.name();
    List<String> babyEssentialsRecords = null;
    HashMap<Integer, String> keyMap = null;
    // HashMap<K, V> to hold BabyEssentialsProd objects.
    HashMap<String, BabyEssentialsProd> BabyEssentialsProducts;

    /**
     * Constructor
     */
    public BabyEssentialsDept() {
	super.setDeptName(deptName);
	// Record Load
	DataCsvLoad unLoadTrucks = new DataCsvLoad();
	unLoadTrucks.loadData(StoreConstants.BABY_ESSENTIALS_TRUCK);
	babyEssentialsRecords = unLoadTrucks.getRecords();
	this.setLoadedRecords(babyEssentialsRecords);
	// System.out.printf("%s Department open with %d records\n", deptName,
	// autoRecords.size());
	keyMap = new HashMap<Integer, String>();
	// Baby Essentials Product Load
	BabyEssentialsProducts = new HashMap<String, BabyEssentialsProd>();
	loadProducts();
    }

    @Override
    protected void loadProducts() {
	// Load products
	for (String record : babyEssentialsRecords) {
	    BabyEssentialsProd bp = new BabyEssentialsProd();
	    boolean recordToProductSuccessful = bp.recordToProduct(record);

	    if (recordToProductSuccessful == true) {
		String prodKey = ProdKeyGen.genKey(bp);
		int howMany = bp.getQuantity();
		for (int i = 0; i < howMany; i++) {
		    // System.out.println(prodKey);
		    BabyEssentialsProducts.put(prodKey + i, bp);
		}
	    }
	    System.out.printf("%s Department loaded %d (crates) and created %d types of products\n", deptName,
		    babyEssentialsRecords.size(), BabyEssentialsProducts.size());
	}
    }

    @Override
    public void listProducts() {
	String aKey = null;
	Set<String> babyProductKeys = BabyEssentialsProducts.keySet();

	int totalProducts = babyProductKeys.size();
	int i = 1;
	for (String pKey : babyProductKeys) {
	    Product pd = BabyEssentialsProducts.get(pKey);
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
	    BabyEssentialsProd pd = BabyEssentialsProducts.get(pKey);
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
	Image babyessentialsImage = new Image(StoreConstants.BABYESSENTIALSDEPT);
	ImageView iv = new ImageView();
	iv.setImage(babyessentialsImage);
	iv.setFitWidth(600);
	iv.setPreserveRatio(true);
	iv.setSmooth(true);
	iv.setCache(true);
	HBox furp = new HBox(iv);
	furp.setAlignment(Pos.CENTER);

	Scene bedScene = new Scene(furp, 600, 575);
	// TODO Auto-generated method stub
	return bedScene;
    }

}

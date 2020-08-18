/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package healthandbeauty;

import util.Product;
import util.StoreConstants;
import util.StoreConstants.deptNames;

/**
 * @author Allma M. Johnson
 *
 */
public class HealthAndBeautyProd extends Product {

    /**
     * 
     */
    public HealthAndBeautyProd() {
	// TODO Auto-generated constructor stub
    }
    /*
     * ProductName , Department , UnitOfMeasure , Barcode , Quantity , Size , Brand
     * , Weight , Price , Color , Ingredient , NumUnitsInstock , Description
     * /*Automotive/Tires private String productName; private deptNames
     * deptNameEnum; private unitOfMeasure unitOfMeasureEnum; private String
     * barCode; private int quantity; private util.StoreConstants.size sizeEnum;
     * private String bandName; private weight weightEnum; private double price;
     * private util.StoreConstants.color colorEnum; private String ingredient;
     * private int numUnitsInstock; private String description;
     */

    @Override
    // records
    public boolean recordToProduct(String record) {
	// TODO Auto-generated method stub
	try {
	    // System.out.println(record);
	    String[] fields = record.split(",");
	    this.setProductName(fields[0].toString());
	    String dName = fields[1].toString().trim();
	    this.setDeptNameEnum(deptNames.valueOf(dName.toUpperCase().trim()));
	    this.setUnitOfMeasureEnum(StoreConstants.unitOfMeasure.valueOf(fields[2].trim()));
	    String barCode = util.RandomUUID.getUuidString();
	    this.setBarCode(barCode);
	    int quantity = Integer.parseInt(fields[4].trim());
	    this.setQuantity(quantity);
	    this.setSize(fields[5].trim());
	    this.setBrandName(fields[6].trim());
	    this.setWeight(fields[7].trim());
	    double price = Double.parseDouble(fields[8].toString());
	    this.setPrice(price);
	    this.setColorEnum(StoreConstants.color.valueOf(fields[9].trim()));
	    this.setIngredient(fields[10].trim());
	    int units = Integer.parseInt(fields[11].trim());
	    this.setNumUnitsInstock(units);
	    this.setDescription(fields[12].trim());
	    return true;
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	    return false;
	}
    }

}

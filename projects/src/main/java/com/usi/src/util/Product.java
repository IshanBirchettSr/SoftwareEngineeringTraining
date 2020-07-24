/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import util.StoreConstants.deptNames;
import util.StoreConstants.unitOfMeasure;

/**
 * @author ibirc
 *
 */
public abstract class Product {

    /**
     * The following data members coincide with the text (truck) files used to load
     * records. Each column is represented here.
     * 
     * @return
     */
    private String productName;
    private deptNames deptNameEnum;
    private unitOfMeasure unitOfMeasureEnum;
    private String barCode;
    private int quantity;
    private String size;
    private String brandName;
    private String weight;
    private double price;
    private util.StoreConstants.color colorEnum;
    private String ingredient;
    private int numUnitsInstock;
    private String description;

    public abstract boolean recordToProduct(String record);

    /**
     * Default Constructor
     */
    public Product() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @return the productName
     */
    public String getProductName() {
	return productName;
    }

    /**
     * @param productName the productName to set
     */
    protected void setProductName(String productName) {
	this.productName = productName;
    }

    /**
     * @return the deptNameEnum
     */
    protected deptNames getDeptNameEnum() {
	return deptNameEnum;
    }

    /**
     * @param deptNameEnum the deptNameEnum to set
     */
    protected void setDeptNameEnum(deptNames deptNameEnum) {
	this.deptNameEnum = deptNameEnum;
    }

    /**
     * @return the unitOfMeasureEnum
     */
    protected unitOfMeasure getUnitOfMeasureEnum() {
	return unitOfMeasureEnum;
    }

    /**
     * @param unitOfMeasureEnum the unitOfMeasureEnum to set
     */
    protected void setUnitOfMeasureEnum(unitOfMeasure unitOfMeasureEnum) {
	this.unitOfMeasureEnum = unitOfMeasureEnum;
    }

    /**
     * @return the barCode
     */
    protected String getBarCode() {
	return barCode;
    }

    /**
     * @param barCode the barCode to set
     */
    protected void setBarCode(String barCode) {
	this.barCode = barCode;
    }

    /**
     * @return the quantity
     */
    protected int getQuantity() {
	return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    protected void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    /**
     * @return the sizeEnum
     */
    protected String getSize() {
	return this.size;
    }

    /**
     * @param sizeEnum the sizeEnum to set
     */
    protected void setSize(String size) {
	this.size = size;
    }

    /**
     * @return the bandName
     */
    protected String getBrandName() {
	return brandName;
    }

    /**
     * @param bandName the bandName to set
     */
    protected void setBrandName(String brandName) {
	this.brandName = brandName;
    }

    /**
     * @return the weightEnum
     */
    protected String getWeight() {
	return weight;
    }

    /**
     * @param weightEnum the weightEnum to set
     */
    protected void setWeight(String weight) {
	this.weight = weight;
    }

    /**
     * @return the price
     */
    public double getPrice() {
	return price;
    }

    /**
     * @param price the price to set
     */
    protected void setPrice(double price) {
	this.price = price;
    }

    /**
     * @return the colorEnum
     */
    protected util.StoreConstants.color getColorEnum() {
	return colorEnum;
    }

    /**
     * @param colorEnum the colorEnum to set
     */
    protected void setColorEnum(util.StoreConstants.color colorEnum) {
	this.colorEnum = colorEnum;
    }

    /**
     * @return the ingredient
     */
    protected String getIngredient() {
	return ingredient;
    }

    /**
     * @param ingredient the ingredient to set
     */
    protected void setIngredient(String ingredient) {
	this.ingredient = ingredient;
    }

    /**
     * @return the numUnitsInstock
     */
    protected int getNumUnitsInstock() {
	return numUnitsInstock;
    }

    /**
     * @param numUnitsInstock the numUnitsInstock to set
     */
    protected void setNumUnitsInstock(int numUnitsInstock) {
	this.numUnitsInstock = numUnitsInstock;
    }

    /**
     * @return the description
     */
    protected String getDescription() {
	return description;
    }

    /**
     * @param description the description to set
     */
    protected void setDescription(String description) {
	this.description = description;
    }

    /**
     * @return
     */
    public boolean loadProducts() {
	// TODO Auto-generated method stub
	return false;
    }

}
/**
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import java.util.List;

/**
 * @author ibirc
 *
 */
public abstract class Department {
    private String deptName = null;
    private int numberOfIlses = 0;
    private int numberOfSections = 0;
    private int numberOfShelves = 0;
    private boolean samples = true;
    private int numberOfDisplays = 0;
    private List<String> loadedRecords = null;

    /**
     * @return the autoRecords
     */
    protected List<String> getLoadedRecords() {
	return loadedRecords;
    }

    /**
     * @param inLoadedRecords the autoRecords to set
     */
    protected void setLoadedRecords(List<String> inLoadedRecords) {
	this.loadedRecords = inLoadedRecords;
    }

    // TODO Auto-generated method stub

    /**
     * @return the deptName
     */
    public String getDeptName() {
	return deptName;
    }

    /**
     * @param deptName the deptName to set
     */
    public void setDeptName(String deptName) {
	this.deptName = deptName;
    }

    /**
     * @return the numberOfIlses
     */
    public int getNumberOfIlses() {
	return numberOfIlses;
    }

    /**
     * @param numberOfIlses the numberOfIlses to set
     */
    public void setNumberOfIlses(int numberOfIlses) {
	this.numberOfIlses = numberOfIlses;
    }

    /**
     * @return the numberOfSections
     */
    public int getNumberOfSections() {
	return numberOfSections;
    }

    /**
     * @param numberOfSections the numberOfSections to set
     */
    public void setNumberOfSections(int numberOfSections) {
	this.numberOfSections = numberOfSections;
    }

    /**
     * @return the numberOfShelves
     */
    public int getNumberOfShelves() {
	return numberOfShelves;
    }

    /**
     * @param numberOfShelves the numberOfShelves to set
     */
    public void setNumberOfShelves(int numberOfShelves) {
	this.numberOfShelves = numberOfShelves;
    }

    /**
     * @return the samples
     */
    public boolean isSamples() {
	return samples;
    }

    /**
     * @param samples the samples to set
     */
    public void setSamples(boolean samples) {
	this.samples = samples;
    }

    /**
     * @return the numberOfDisplays
     */
    public int getNumberOfDisplays() {
	return numberOfDisplays;
    }

    /**
     * @param numberOfDisplays the numberOfDisplays to set
     */
    public void setNumberOfDisplays(int numberOfDisplays) {
	this.numberOfDisplays = numberOfDisplays;
    }

    protected abstract void loadProducts();

}

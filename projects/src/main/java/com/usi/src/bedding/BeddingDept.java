/**
 * 
 */
package bedding;

import util.Department;

/**
 * @author Allma M. Johnson
 *
 */
public class BeddingDept extends Department {

    private int numberOfFittedSheets = 5;
    private int numberOfFlatSheets = 4;
    private int numberOfPillowCases = 6;
    private int numberOfTwinFittedSheets = 3;
    private int numberOfTwinFlatSheets = 4;
    private int numberOfFullFittedSheets = 3;
    private int numberOfKingFlatSheets = 6;
    private boolean hasAllSheetSizes = false;
    private boolean hasSheetsInStock = true;

    public int getNumberOfFittedSheets() {
	return numberOfFittedSheets;
    }

    public void setNumberOfFittedSheets(int numberOfFittedSheets) {
	this.numberOfFittedSheets = numberOfFittedSheets;
    }

    public int getNumberOfFlatSheets() {
	return numberOfFlatSheets;
    }

    public void setNumberOfFlatSheets(int numberOfFlatSheets) {
	this.numberOfFlatSheets = numberOfFlatSheets;
    }

    public int getNumberOfPillowCases() {
	return numberOfPillowCases;
    }

    public void setNumberOfPillowCases(int numberOfPillowCases) {
	this.numberOfPillowCases = numberOfPillowCases;
    }

    public int getNumberOfTwinFittedSheets() {
	return numberOfTwinFittedSheets;
    }

    public void setNumberOfTwinFittedSheets(int numberOfTwinFittedSheets) {
	this.numberOfTwinFittedSheets = numberOfTwinFittedSheets;
    }

    public int getNumberOfTwinFlatSheets() {
	return numberOfTwinFlatSheets;
    }

    public void setNumberOfTwinFlatSheets(int numberOfTwinFlatSheets) {
	this.numberOfTwinFlatSheets = numberOfTwinFlatSheets;
    }

    public int getNumberOfFullFittedSheets() {
	return numberOfFullFittedSheets;
    }

    public void setNumberOfFullFittedSheets(int numberOfFullFittedSheets) {
	this.numberOfFullFittedSheets = numberOfFullFittedSheets;
    }

    public int getNumberOfKingFlatSheets() {
	return numberOfKingFlatSheets;
    }

    public void setNumberOfKingFlatSheets(int numberOfKingFlatSheets) {
	this.numberOfKingFlatSheets = numberOfKingFlatSheets;
    }

    public boolean isHasAllSheetSizes() {
	return hasAllSheetSizes;
    }

    public void setHasAllSheetSizes(boolean hasAllSheetSizes) {
	this.hasAllSheetSizes = hasAllSheetSizes;
    }

    public boolean isHasSheetsInStock() {
	return hasSheetsInStock;
    }

    public void setHasSheetsInStock(boolean hasSheetsInStock) {
	this.hasSheetsInStock = hasSheetsInStock;
    }

    /**
     * 
     */
    public BeddingDept() {

    }

    @Override
    protected void loadProducts() {
	// TODO Auto-generated method stub
    }

}

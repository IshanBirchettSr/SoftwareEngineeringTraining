/**
 * 
 */
package haircare;

import java.util.List;

import util.Department;
import util.Product;

/**
 * @author Allma M. Johnson
 *
 */
public class Haircolor extends Department {

    private int numberOfBlack = 4;
    private int numberOfBlond = 4;
    private int numberOfDarkBlond = 4;
    private int numberOfLightBlond = 4;
    private int numberOfAuburn = 4;
    private int numberOfDarkAuburn = 4;
    private int numberOfLightAuburnn = 4;
    private int numberOfBrown = 4;
    private int numberOfDarkBrown = 4;
    private int numberOfLightBrown = 4;
    public boolean hasHairColorInStock = true;

    /**
     * 
     */
    public Haircolor() {

    }

    public int getNumberOfBlack() {
	return numberOfBlack;
    }

    public void setNumberOfBlack(int numberOfBlack) {
	this.numberOfBlack = numberOfBlack;
    }

    public int getNumberOfBlond() {
	return numberOfBlond;
    }

    public void setNumberOfBlond(int numberOfBlond) {
	this.numberOfBlond = numberOfBlond;
    }

    public int getNumberOfDarkBlond() {
	return numberOfDarkBlond;
    }

    public void setNumberOfDarkBlond(int numberOfDarkBlond) {
	this.numberOfDarkBlond = numberOfDarkBlond;
    }

    public int getNumberOfLightBlond() {
	return numberOfLightBlond;
    }

    public void setNumberOfLightBlond(int numberOfLightBlond) {
	this.numberOfLightBlond = numberOfLightBlond;
    }

    public int getNumberOfAuburn() {
	return numberOfAuburn;
    }

    public void setNumberOfAuburn(int numberOfAuburn) {
	this.numberOfAuburn = numberOfAuburn;
    }

    public int getNumberOfDarkAuburn() {
	return numberOfDarkAuburn;
    }

    public void setNumberOfDarkAuburn(int numberOfDarkAuburn) {
	this.numberOfDarkAuburn = numberOfDarkAuburn;
    }

    public int getNumberOfLightAuburnn() {
	return numberOfLightAuburnn;
    }

    public void setNumberOfLightAuburnn(int numberOfLightAuburnn) {
	this.numberOfLightAuburnn = numberOfLightAuburnn;
    }

    public int getNumberOfBrown() {
	return numberOfBrown;
    }

    public void setNumberOfBrown(int numberOfBrown) {
	this.numberOfBrown = numberOfBrown;
    }

    public int getNumberOfDarkBrown() {
	return numberOfDarkBrown;
    }

    public void setNumberOfDarkBrown(int numberOfDarkBrown) {
	this.numberOfDarkBrown = numberOfDarkBrown;
    }

    public int getNumberOfLightBrown() {
	return numberOfLightBrown;
    }

    public void setNumberOfLightBrown(int numberOfLightBrown) {
	this.numberOfLightBrown = numberOfLightBrown;
    }

    public boolean isHasHairColorInStock() {
	return hasHairColorInStock;
    }

    public void setHasHairColorInStock(boolean hasHairColorInStock) {
	this.hasHairColorInStock = hasHairColorInStock;
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

}

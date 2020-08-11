/**
 * 
 */
package housewares;

import java.util.List;

import javafx.scene.Scene;
import util.Department;
import util.Product;

/**
 * @author Allma M. Johnson
 *
 */
public class Kitchen extends Department {

    private int numberOfCoffeeMakers = 3;
    private int numberOfHandMixers = 2;
    private int numberOfSpatulas = 4;
    private boolean hasSkillets = true;

    /**
     * 
     */
    public Kitchen() {

    }

    public int getNumberOfCoffeeMakers() {
	return numberOfCoffeeMakers;
    }

    public void setNumberOfCoffeeMakers(int numberOfCoffeeMakers) {
	this.numberOfCoffeeMakers = numberOfCoffeeMakers;
    }

    public int getNumberOfHandMixers() {
	return numberOfHandMixers;
    }

    public void setNumberOfHandMixers(int numberOfHandMixers) {
	this.numberOfHandMixers = numberOfHandMixers;
    }

    public int getNumberOfSpatulas() {
	return numberOfSpatulas;
    }

    public void setNumberOfSpatulas(int numberOfSpatulas) {
	this.numberOfSpatulas = numberOfSpatulas;
    }

    public boolean isHasSkillets() {
	return hasSkillets;
    }

    public void setHasSkillets(boolean hasSkillets) {
	this.hasSkillets = hasSkillets;
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

    @Override
    public List<Product> getProds(int index, int quantity) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Scene getScene() {
	// TODO Auto-generated method stub
	return null;
    }

}

/**
 * 
 */
package housewares;

import util.Department;

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
    protected int loadProduct() {
	// TODO Auto-generated method stub
	return 0;
    }

}

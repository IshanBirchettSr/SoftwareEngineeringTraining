/**
 * 
 */
package bikes;

import util.Department;

/**
 * @author Allma M. Johnson
 *
 */
public class MountainBikes extends Department {

    private int numberOfGirlsMountainBikes = 5;
    private int numberOfBoysMountainBikes = 8;
    private int numberOfWomensMountainBikes = 4;
    private int numberOfMensMountainBikes = 3;
    private boolean hasAllSizesOfHelmets = true;

    /**
     * 
     */
    public MountainBikes() {

    }

    public int getNumberOfGirlsMountainBikes() {
	return numberOfGirlsMountainBikes;
    }

    public void setNumberOfGirlsMountainBikes(int numberOfGirlsMountainBikes) {
	this.numberOfGirlsMountainBikes = numberOfGirlsMountainBikes;
    }

    public int getNumberOfBoysMountainBikes() {
	return numberOfBoysMountainBikes;
    }

    public void setNumberOfBoysMountainBikes(int numberOfBoysMountainBikes) {
	this.numberOfBoysMountainBikes = numberOfBoysMountainBikes;
    }

    public int getNumberOfWomensMountainBikes() {
	return numberOfWomensMountainBikes;
    }

    public void setNumberOfWomensMountainBikes(int numberOfWomensMountainBikes) {
	this.numberOfWomensMountainBikes = numberOfWomensMountainBikes;
    }

    public int getNumberOfMensMountainBikes() {
	return numberOfMensMountainBikes;
    }

    public void setNumberOfMensMountainBikes(int numberOfMensMountainBikes) {
	this.numberOfMensMountainBikes = numberOfMensMountainBikes;
    }

    public boolean isHasAllSizesOfHelmets() {
	return hasAllSizesOfHelmets;
    }

    public void setHasAllSizesOfHelmets(boolean hasAllSizesOfHelmets) {
	this.hasAllSizesOfHelmets = hasAllSizesOfHelmets;
    }

    @Override
    protected int loadProduct() {
	// TODO Auto-generated method stub
	return 0;
    }

}

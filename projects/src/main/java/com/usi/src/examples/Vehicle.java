/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package examples;

/**
 * @author ibirc
 *
 */
public class Vehicle {

    private boolean hasSteeringWheel = true;
    private boolean hasBrakes = true;
    private int headLights = 2;
    private int numberOfWheels = 4;
    private boolean parkBrake = true;
    private int numberOfSeats = 5;

//    private Engine eg = null;
    // private Transmission tm = null;

    /**
     * @return the hasSteeringWheel
     */
    public boolean isHasSteeringWheel() {
	return hasSteeringWheel;
    }

    /**
     * @param hasSteeringWheel the hasSteeringWheel to set
     */
    public void setHasSteeringWheel(boolean hasSteeringWheel) {
	this.hasSteeringWheel = hasSteeringWheel;
    }

    /**
     * @return the hasBrakes
     */
    public boolean isHasBrakes() {
	return hasBrakes;
    }

    /**
     * @param hasBrakes the hasBrakes to set
     */
    public void setHasBrakes(boolean hasBrakes) {
	this.hasBrakes = hasBrakes;
    }

    /**
     * @return the headLights
     */
    protected int getHeadLights() {
	return headLights;
    }

    /**
     * @param headLights the headLights to set
     */
    public void setHeadLights(int headLights) {
	this.headLights = headLights;
	this.getHeadLights();
    }

    /**
     * @return the numberOfWheels
     */
    public int getNumberOfWheels() {
	return numberOfWheels;
    }

    /**
     * @param numberOfWheels the numberOfWheels to set
     */
    public void setNumberOfWheels(int numberOfWheels) {
	this.numberOfWheels = numberOfWheels;
    }

    /**
     * @return the parkBrake
     */
    public boolean isParkBrake() {
	return parkBrake;
    }

    /**
     * @param parkBrake the parkBrake to set
     */
    public void setParkBrake(boolean parkBrake) {
	this.parkBrake = parkBrake;
    }

    /**
     * @return the numberOfSeats
     */
    public int getNumberOfSeats() {
	return numberOfSeats;
    }

    /**
     * @param numberOfSeats the numberOfSeats to set
     */
    public void setNumberOfSeats(int numberOfSeats) {
	this.numberOfSeats = numberOfSeats;
    }

    /**
     * 
     */
    public Vehicle() {

    }

}

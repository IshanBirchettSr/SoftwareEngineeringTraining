/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package customerservice;

/**
 * @author Allma M. Johnson
 *
 */

public class AutomotiveArrayList extends SmartCart {

    class SmartCart extends CustomerServiceDept {

	// Random Super Store friendly reminder.
	public void main(String[] args) {
	    System.out.println(
		    "An Oil Change is recommended every 5,000 miles. See one of our five star Automotive Mechanics to get your Oil Change today!");

	    // Super Store Automotive Array List w/index reference
	    String automotiveTires[] = { "Good Year Tires", "Firestone", "Michelin" };
	    String automotiveMotorOil[] = { "Shell Rotella", "Motordraft", "Mobil" };
	    String automotiveTransmissionFluid[] = { "Valvoline", "Mobil", "Castrol" };
	    String automotiveBrakeFluid[] = { "Prestone", "Castrol", "Mobil" };

	    System.out.println("Tires");
	    System.out.println(automotiveTires[0]);
	    System.out.println(automotiveTires[1]);
	    System.out.println(automotiveTires[2]);

	    System.out.println("Motor Oil");
	    System.out.println(automotiveMotorOil[0]);
	    System.out.println(automotiveMotorOil[1]);
	    System.out.println(automotiveMotorOil[2]);

	    System.out.println("Transmission Fluid");
	    System.out.println(automotiveTransmissionFluid[0]);
	    System.out.println(automotiveTransmissionFluid[1]);
	    System.out.println(automotiveTransmissionFluid[2]);

	    System.out.println("Brake Fluid");
	    System.out.println(automotiveBrakeFluid[0]);
	    System.out.println(automotiveBrakeFluid[1]);
	    System.out.println(automotiveBrakeFluid[2]);

	}
    }

    public AutomotiveArrayList() {
	// TODO Auto-generated constructor stub
    }

}

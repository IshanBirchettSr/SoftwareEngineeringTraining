/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package customerservice;

import java.util.Scanner;
import java.util.UUID;

/**
 * @author ibirc
 *
 */
public class MembershipSignUp {

	Scanner in = null;

	/**
	 * 
	 */
	public MembershipSignUp(Scanner inComing) {
		super();
		in = inComing;
	}

	public String membershipApplication() {
		System.out.println("Please enter your First Name: ");
		String firstName = in.next();
		System.out.println("Please enter your Middle Name (optional): ");
		String middleName = in.next();
		System.out.println("Please enter your Last Name: ");
		String lastName = in.next();
		String membershipId = String.format("Customer: %s %s %s Member Ship ID is: %s, enjoy your discounts!\n",
				firstName, middleName, lastName, UUID.randomUUID().toString());
		return membershipId;

	}

}

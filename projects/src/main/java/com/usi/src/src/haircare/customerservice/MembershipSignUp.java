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

import util.StoreConstants;

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
		System.out.println("Please enter your Middle Name : ");
		String middleName = in.next();
		System.out.println("Please enter your Last Name: ");
		String lastName = in.next();
		String membershipId = String.format(
				"Customer: %s %s %s Membership ID is: %s, enjoy your %d%% discount starting today!\n", firstName,
				middleName, lastName, UUID.randomUUID().toString(), StoreConstants.TODAYS_MEMBER_DISCOUNT);
		return membershipId;
	}

}

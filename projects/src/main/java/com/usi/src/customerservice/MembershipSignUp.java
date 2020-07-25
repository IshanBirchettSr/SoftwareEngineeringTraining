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
    // put all data members declarations here
    int phoneNumber = 0;

    /**
     
     */
    public MembershipSignUp(Scanner inComing) {
	in = inComing;
    }

    /**
     * @return the phoneNumber
     */
    public int getPhoneNumber() {
	return phoneNumber;
    }

    /**
     * @param mobilenumber the phoneNumber to set
     */
    public void setPhoneNumber(int phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String membershipApplication() {
	System.out.println("Please enter your First Name: ");
	String firstName = in.next();
	System.out.println("Please enter your Middle Initial : ");
	String middleName = in.next();
	System.out.println("Please enter your Last Name: ");
	String lastName = in.next();
	System.out.println("Please enter your email address: ");
	String emailaddress = in.next();
	System.out.println("Please enter your mailing address: ");
	String mailingaddress = in.next();
	System.out.println("Please enter your phone number: ");
	phoneNumber = in.nextInt();

	String membershipId = String.format(

		"Customer: %s %s %s Membership ID is: %s, enjoy your %d%% discount starting today!\n", firstName,
		middleInitial, lastName, emailAddress, mailingAddress, phoneNumber, UUID.randomUUID().toString(),
		StoreConstants.TODAYS_MEMBER_DISCOUNT);
	return membershipId;
    }

}

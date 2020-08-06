/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package customerservice;

import java.util.Calendar;

/**
 * @author ibirc
 *
 */
public class MembershipSignUp {
    // put all data members declarations here
    private String firstName;
    private char mInitial;
    private String lastName;
    private String emailAddress;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String phoneNumber;
    private boolean aarpMember;

    /**
     * @return the firstName
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    /**
     * @return the mInitial
     */
    public char getmInitial() {
	return mInitial;
    }

    /**
     * @param mInitial the mInitial to set
     */
    public void setmInitial(char mInitial) {
	this.mInitial = mInitial;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
	return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
	return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
	this.streetAddress = streetAddress;
    }

    /**
     * @return the city
     */
    public String getCity() {
	return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
	this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
	return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
	this.state = state;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
	return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
    }

    /**
     * @return the aarpMember
     */
    public boolean isAarpMember() {
	return aarpMember;
    }

    /**
     * @param aarpMember the aarpMember to set
     */
    public void setAarpMember(boolean aarpMember) {
	this.aarpMember = aarpMember;
    }

    /**
     * @return the dateOfMembership
     */
    public Calendar getDateOfMembership() {
	return dateOfMembership;
    }

    /**
     * @param dateOfMembership the dateOfMembership to set
     */
    public void setDateOfMembership(Calendar dateOfMembership) {
	this.dateOfMembership = dateOfMembership;
    }

    private Calendar dateOfMembership;

    /**
     
     */
    public MembershipSignUp(String memRecord) {
	LoadDataMember(memRecord);
    }

    /**
     * @param memRecord
     */
    private void LoadDataMember(String memRecord) {
	String[] members = memRecord.split(",");
	setFirstName(members[0].trim());
	// *add set for all data members 1-?
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
	return phoneNumber;
    }

    /**
     * @param mobilenumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

}

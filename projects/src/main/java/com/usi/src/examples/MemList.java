/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package examples;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import customerservice.MembershipSignUp;

/**
 * @author chich
 *
 */
@XmlRootElement
public class MemList {

    List<MembershipSignUp> list = null;

    /**
     * @param memRecord
     */
    public MemList(String memRecord) {

	// TODO Auto-generated constructor stub
    }

    @XmlElement
    public List<MembershipSignUp> getMembership() {

	return list;

    }

    public void setMembershipSignUp(List<MembershipSignUp> inList) {
	list = inList;

    }

    /**
     * 
     */
    public MemList() {

	list = new ArrayList<MembershipSignUp>();
	// TODO Auto-generated constructor stub
    }

}

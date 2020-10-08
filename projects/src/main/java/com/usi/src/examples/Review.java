/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package examples;

import java.util.List;

import automotive.AutomotiveDept;
import dairy.DairyDept;
import javafx.scene.Scene;
import util.Department;
import util.Product;

/**
 * @author ibirc
 *
 */
public class Review extends SampleThis {

    public String name = "David";
    protected int age = 24;
    public String ishan = "Ishan";
    static SampleThis st = null;
    // Has-a relationship
    DairyDept dd = null;
    AutomotiveDept ad = null;

    /**
     * 
     */
    public Review() {
	// TODO Auto-generated constructor stub
	dd = new DairyDept();
	ad = new AutomotiveDept();
    }

    private void run() {
	// System.out.printf("Box Name: %s\n", SampleStatic.boxName);
	st = new SampleThis();
	System.out.printf("Ishan Name from Run: %s\n", st.getIshan());
	whichDept(dd);
	whichDept(ad);
	System.out.printf("Before Age: %d\n", age);
	System.out.printf("Age: %d\n", --age);
	System.out.printf("After Age: %d\n", --age);
	System.out.printf("Age: %d\n", --age);
	int t = 10;
	// whileDemo(t);
	// whileDemo(t, 2);
	whileDemo(t, 2, "Ishan");
	// 1 - Front room lamp on
	// 2 - Garage door open
	// 3 - From porch light on
	// 4 - Sprinkler on
	// 00000001;
	// 00000010;
	// 00000100;
	// 00001000;
	// 00001010;
	// 00001010;
	// 00000101;
	// 00000010;
	// 00010100;
	int r = t << 1;
	System.out.printf("Shift by 1 %d\n", r);
	whichOne("Charlene");
//	for (int i=0; i<10; i++) {
//	
//	}
    }

    /**
     * @return the ishan
     */
    public String getIshan() {
	return ishan;
    }

    public void whichDept(Department dt) {
	if (dt instanceof DairyDept) {
	    System.out.println("It is-a instance of Dairy\n");
	} else {
	    if (dt instanceof AutomotiveDept) {
		System.out.println("It is-a instance of Automotive\n");
	    } else {
		System.out.println("This is-a unknown type\n");
	    }
	}
    }

    /**
     * @param ishan the ishan to set
     */
    public void setIshan(String ishan) {
	this.ishan = ishan;
    }

    public void whichOne(String t) {

	switch (t) {
	case "Roxanne":
	    System.out.printf("%s is supported\n", t);
	    break;
	case "Allma":
	    System.out.printf("%s is supported\n", t);
	    break;
	case "Charlene":
	    System.out.printf("%s supported\n", t);
	    break;
	default: {
	    System.out.printf("Not supported %d\n", t);
	    break;
	}
	}
    }

    public void whileDemo(int max) {
	int i = 0;

	while (i < max) {
	    System.out.printf("Counter: %d\n", i);
	    i++;
	}
	System.out.printf("i is no longer less than max %d\n", max);
    }

    public void whileDemo(int max, int ivalue) {
	int i = ivalue;

	while (i < max) {
	    System.out.printf("Counter: %d\n", i);
	    i++;
	}
	System.out.printf("i is no longer less than max %d\n", max);
    }

    public void whileDemo(int max, int ivalue, String name) {
	int i = ivalue;

	while (i < max) {
	    System.out.printf("%s: %d\n", name, i);
	    i++;
	}
	System.out.printf("i is no longer less than max %d\n", max);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	Review app = new Review();
	// System.out.printf("Box Name: %s\n", SampleStatic.boxName);
	SampleStatic.setBoxName("FedEX");
	app.run();
	// st.getIshan();
	System.out.printf("Ishan Name from Main: %s\n", st.getIshan());

    }

}

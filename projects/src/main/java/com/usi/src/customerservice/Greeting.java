package customerservice;

import java.util.HashMap;
import java.util.Scanner;

import util.StoreConstants;
import util.YesNoInput;

public class Greeting {
    HashMap<Integer, MembershipSignUp> membershipCards = null;

    /**
     * 
     */
    public Greeting() {
	membershipCards = new HashMap<Integer, MembershipSignUp>();
	loadCards();

    }

    public void loadCards() {

    }


    public Customer sayGreeting() {
	Scanner in = new Scanner(System.in);
	Customer cust = null;

	System.out.printf("Welcome to %s\n", StoreConstants.STORE_NAME);
	System.out.printf("Do you have a %s Membership Card? ", StoreConstants.STORE_NAME);
	boolean member = YesNoInput.stringToBoolean(in.next());
	if (member == true) {
	    // membershipCards.get();
	    // get phone number from user
	    // get membership card
	    // return customer object
	    System.out.printf("Great! Today's discount is %d%%, Happy Shopping!!\n",
		    StoreConstants.TODAYS_MEMBER_DISCOUNT);
	} else {
	    System.out.println("Would you like to sign up for a membership card?");
	    boolean signUp = YesNoInput.stringToBoolean(in.next());
	    if (signUp == true) {
		// in.nextLine();
		MembershipSignUp memSignUp = new MembershipSignUp(in);
		String memInfo = memSignUp.membershipApplication();
		membershipCards.put(memSignUp.getPhoneNumber(), memSignUp);
		// return customer object
		System.out.println(memInfo);
	    } else {
		System.out.printf(
			"Ok, well you can signup at any time the future and instantly save %d%%.\nHappy Shopping\n",
			StoreConstants.TODAYS_MEMBER_DISCOUNT);
      // We will need to still have to create and return a anonomus Customer object to represent customers who do not have memberships.
	    }
    	
	}
	return cust;
}
}

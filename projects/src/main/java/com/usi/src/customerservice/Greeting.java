package customerservice;

import java.util.Scanner;

import util.StoreConstants;
import util.YesNoInput;

public class Greeting {

	public void sayGreeting() {
		Scanner in = new Scanner(System.in);
		System.out.printf("Welcome to %s\n", StoreConstants.STORE_NAME);
		System.out.printf("Do you have a %s Membership Card? ", StoreConstants.STORE_NAME);
		boolean member = YesNoInput.stringToBoolean(in.next());
		if (member == true) {
			System.out.printf("Great! Today's discount is %d%%, Happy Shopping!!\n",
					StoreConstants.TODAYS_MEMBER_DISCOUNT);
		} else {
			System.out.println("Would you like to sign up for a membership card?");
			boolean signUp = YesNoInput.stringToBoolean(in.next());
			if (signUp == true) {
				// in.nextLine();
				MembershipSignUp memSignUp = new MembershipSignUp(in);
				String memInfo = memSignUp.membershipApplication();
				System.out.println(memInfo);
			} else {
				System.out.printf(
						"Ok, well you can signup at any time the future and instantly save %d%%.\nHappy Shopping\n",
						StoreConstants.TODAYS_MEMBER_DISCOUNT);

			}
		}
	}
}

package customerservice;

import util.DataCsvLoad;
import util.StoreConstants;

public class SuperStore {
	boolean status = false;

	public boolean openStore() {
		DataCsvLoad unLoadTrucks = new DataCsvLoad(StoreConstants.VEGETABLE_TRUCK);

		boolean status = false;
		Greeting greeting = new Greeting();
		greeting.sayGreeting();
		return status;

	}

	private boolean setStatus(boolean storeOpen) {
		return status = storeOpen;
	}

	public static void main(String[] args) {
		SuperStore store = new SuperStore();

		store.openStore();
	}

}

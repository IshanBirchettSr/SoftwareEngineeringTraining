package util;

import java.util.UUID;

public class RandomUUID {

    public static String getUuidString() {
	// Creating a random UUID (Universally unique identifier).
	// UUID uuid = UUID.randomUUID();
	String randomUUIDString = UUID.randomUUID().toString();

//	System.out.println("Random UUID String = " + randomUUIDString);
//	System.out.println("UUID version       = " + uuid.version());
//	System.out.println("UUID variant       = " + uuid.variant());
	return randomUUIDString;
    }
}
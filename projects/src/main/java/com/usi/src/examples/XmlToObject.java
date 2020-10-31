package examples;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import customerservice.Customer;
import customerservice.MembershipSignUp;

public class XmlToObject {
    public static void main(String[] args) {

	try {

	    File file = new File("MemList.xml");
	    JAXBContext jaxbContext = JAXBContext.newInstance(MemList.class);

	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    MemList mem = (MemList) jaxbUnmarshaller.unmarshal(file);

	    // System.out.println(cust.getmCard() + " " + cust.getmInitial());
	    // System.out.println("Answers:");
	    List<MembershipSignUp> list = (List<MembershipSignUp>) mem.getMembership();
	    for (MembershipSignUp mem1 : list)
		System.out.println(mem1.getFirstName() + " " + mem1.getLastName() + " " + mem1.getmInitial());

	} catch (JAXBException e) {
	    e.printStackTrace();
	}

    }
}
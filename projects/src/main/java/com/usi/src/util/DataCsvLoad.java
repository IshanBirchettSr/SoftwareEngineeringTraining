/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import customerservice.MembershipSignUp;

/**
 * @author ibirc
 *
 */
public class DataCsvLoad extends DataLoad {

    private List<String> records = new ArrayList<>();
    private List<String> MemRecords = new ArrayList<>();

    /**
     * 
     */
    public DataCsvLoad() {
    }

    public void loadData(String fileName) {
	Path pathToFile = Paths.get(fileName);
	try {
	    BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);

	    String line;

	    while ((line = br.readLine()) != null) {
		if (line.length() == 0) {
		    continue;
		}
		byte[] firstChar = line.getBytes();

		if (firstChar[0] == '/') {
		    continue;
		}
		records.add(line);
	    }

	} catch (NoSuchFileException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadDbRecords(String tablename) {
	String url = StoreConstants.DB_URL;
	String asde9q = StoreConstants.THING1;
	String d834j3 = StoreConstants.THING2;

	String query = "SELECT * FROM " + tablename;

	try (Connection con = DriverManager.getConnection(url, asde9q, d834j3);
		PreparedStatement pst = con.prepareStatement(query)) {

	    ResultSet rs = pst.executeQuery();
	    while (rs.next()) {
		String field1 = rs.getString(1);
		String field2 = rs.getString(2);
		String field3 = rs.getString(3);
		String field4 = rs.getString(4);
		String field5 = rs.getString(5);
		String field6 = rs.getString(6);
		String field7 = rs.getString(7);
		String field8 = rs.getString(8);
		String field9 = rs.getString(9);
		String field10 = rs.getString(10);
		String field11 = rs.getString(11);
		String field12 = rs.getString(12);
		String field13 = rs.getString(13);
		String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s, %s, %s", field1, field2, field3, field4,
			field5, field6, field7, field8, field9, field10, field11, field12, field13);
		// System.out.println(line);
		records.add(line);
	    }
	} catch (SQLException ex) {

	    Logger lgr = Logger.getLogger(DataCsvLoad.class.getName());
	    lgr.log(Level.SEVERE, ex.getMessage(), ex);
	}

    }

    public void InsertMemberInfo(MembershipSignUp nMC) {
	String url = StoreConstants.DB_URL;
	String greatness = StoreConstants.THING1;
	String excellence = StoreConstants.THING2;

	String query = String.format(StoreConstants.QUERY1, nMC.getFirstName(), nMC.getmInitial(), nMC.getLastName(),
		nMC.getEmailAddress(), nMC.getStreetAddress(), nMC.getCity(), nMC.getState(), nMC.getPostalCode(),
		nMC.getPhoneNumber(), nMC.isAarpMember(), nMC.getDateOfMembership());

	try (Connection con = DriverManager.getConnection(url, greatness, excellence);
		PreparedStatement pst = con.prepareStatement(query)) {

	    System.out.print(query);
	    int modified = pst.executeUpdate(query);

	} catch (SQLException ex) {

	    Logger lgr = Logger.getLogger(DataCsvLoad.class.getName());
	    lgr.log(Level.SEVERE, ex.getMessage(), ex);
	}

    }

    public List<String> getRecords() {
	return records;
    }

}
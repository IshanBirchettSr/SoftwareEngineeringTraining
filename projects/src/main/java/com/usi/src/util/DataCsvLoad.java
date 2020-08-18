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
import java.util.ArrayList;
import java.util.List;

/**
 * @author ibirc
 *
 */
public class DataCsvLoad extends DataLoad {

    private List<String> records = new ArrayList<>();

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

    public List<String> getRecords() {
	return records;
    }

}
package com.ashugupta.pjp.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.text.DateFormatter;

import com.ashugupta.pjp.models.Transaction;

public class CSVManager implements DataReader {

	String filePath;

	public CSVManager(String path) {
		filePath = path;
	}

	public CSVManager() {

	}
	
	@Override
	public List<Transaction> readFrom(String path) {
		File file = new File(path);
		FileReader fr;
		BufferedReader br;

		List<Transaction> transactions = new ArrayList<Transaction>();

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = "";
			String[] tempArr;
			int count = 0;
			while ((line = br.readLine()) != null) {
				tempArr = line.split(",");
				if (count == 0) {
					count++;
					continue;
				}
				Date transactionDate = null;
				try {
					transactionDate = new SimpleDateFormat("MM/dd/yyyy").parse(tempArr[4]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double marketValue = Double.parseDouble(tempArr[5]);
				Character priority = Character.valueOf(tempArr[6].charAt(0));

				Transaction transaction = new Transaction(tempArr[0], tempArr[1], tempArr[2], tempArr[3],
						transactionDate, marketValue, priority);
				System.out.println(transaction);
				transactions.add(transaction);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}
	
	@Override
	public void writeTo(String path,List<String[]> summary) throws FileNotFoundException {
		File csvOutputFile = new File(path);
	    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
	        summary.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    }
	}
	
	private String convertToCSV(String[] data) {
	    return Stream.of(data)
	      .map(this::escapeSpecialCharacters)
	      .collect(Collectors.joining(","));
	}
	
	private String escapeSpecialCharacters(String data) {
	    String escapedData = data.replaceAll("\\R", " ");
	    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
	        data = data.replace("\"", "\"\"");
	        escapedData = "\"" + data + "\"";
	    }
	    return escapedData;
	}

}

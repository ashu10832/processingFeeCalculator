package com.ashugupta.pjp.runner;


import java.io.FileNotFoundException;
import java.util.List;

import com.ashugupta.pjp.models.Transaction;
import com.ashugupta.pjp.reader.CSVManager;
import com.ashugupta.pjp.reader.DataReader;
import com.ashugupta.pjp.services.Processor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DataReader dataReader = new CSVManager();
        List<Transaction> transactions =  dataReader.readFrom("Sample_Data_Fee_Calculator.csv");
        Processor processor = new Processor();
        transactions = processor.processTransactions(transactions);
        try {
			dataReader.writeTo("summary.csv", processor.generateSummaryReport(transactions));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }
}

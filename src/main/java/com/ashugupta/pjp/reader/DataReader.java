package com.ashugupta.pjp.reader;

import java.io.FileNotFoundException;
import java.util.List;

import com.ashugupta.pjp.models.Transaction;

public interface DataReader {
	
	public List<Transaction> readFrom(String path);
	
	public void writeTo(String path, List<String[]> data) throws FileNotFoundException;

}

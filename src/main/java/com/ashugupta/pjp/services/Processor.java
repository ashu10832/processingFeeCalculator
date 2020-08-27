package com.ashugupta.pjp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ashugupta.pjp.models.Transaction;

public class Processor {

	public Processor() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Transaction> processTransactions(List<Transaction> transactions){
		for (Transaction transaction : transactions) {
			if (isIntraDay(transaction, transactions)) {
				transaction.setProcessing_fee(10D);
			} else {
				if (transaction.isHighPriority()) {
					transaction.setProcessing_fee(500D);
				} else if (transaction.isSell() || transaction.isWithdraw()) {
					transaction.setProcessing_fee(100D);
				}
				else {
					transaction.setProcessing_fee(50D);
				}
			}
		}
		return transactions;
	}

	public List<String[]> generateSummaryReport(List<Transaction> transactions) {
		List<String[]> summary = new ArrayList<>();
		for (Transaction t  : transactions) {
			summary.add(t.getSummary());
		}
		return summary;
	}
	

	


	private boolean isIntraDay(Transaction transaction, List<Transaction> transactions) {
		Transaction tempTransaction = new Transaction();

		Transaction intradayTransaction = transactions.stream().filter(trans -> intradayCriteria(transaction, trans))
				.findAny().orElse(null);
		if (intradayTransaction == null) {
			return false;
		}
		return true;
	}

	private boolean intradayCriteria(Transaction trans1, Transaction trans2) {
		boolean flag = trans1.getClientId() == trans2.getClientId() && trans1.getSecurityId() == trans2.getSecurityId()
				&& trans1.getTransaction_date().getDay() == trans2.getTransaction_date().getDay()
				&& trans1.getTransaction_date().getMonth() == trans2.getTransaction_date().getMonth()
				&& trans1.getTransaction_date().getYear() == trans2.getTransaction_date().getYear();
		if (flag) {
			if (trans1.getTransactionType().equals("BUY")) {
				if (trans2.getTransactionType().equals("SELL")) {
					return true;
				}
			}
			if (trans1.getTransactionType().equals("SELL")) {
				if (trans2.getTransactionType().equals("BUY")) {
					return true;
				}
			}
		}
		return false;
	}

}

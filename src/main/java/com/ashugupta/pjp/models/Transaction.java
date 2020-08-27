package com.ashugupta.pjp.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	String external_transaction_id;
	String clientId;
	String securityId;
	String transactionType;
	Date transaction_date;
	Double marketValue;
	Character priority_flag;
	Double processing_fee;
	
	public Double getProcessing_fee() {
		return processing_fee;
	}
	public void setProcessing_fee(Double processing_fee) {
		this.processing_fee = processing_fee;
	}
	public String getExternal_transaction_id() {
		return external_transaction_id;
	}
	public void setExternal_transaction_id(String external_transaction_id) {
		this.external_transaction_id = external_transaction_id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	public Double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	public Character getPriority_flag() {
		return priority_flag;
	}
	public void setPriority_flag(Character priority_flag) {
		this.priority_flag = priority_flag;
	}
	
	public boolean isHighPriority() {
		return priority_flag.equals(Character.valueOf('Y'));
	}
	@Override
	public String toString() {
		return "Transaction [external_transaction_id=" + external_transaction_id + ", clientId=" + clientId
				+ ", securityId=" + securityId + ", transactionType=" + transactionType + ", transaction_date="
				+ transaction_date + ", marketValue=" + marketValue + ", priority_flag=" + priority_flag + "]";
	}
	public Transaction(String external_transaction_id, String clientId, String securityId, String transactionType,
			Date transaction_date, Double marketValue, Character priority_flag) {
		super();
		this.external_transaction_id = external_transaction_id;
		this.clientId = clientId;
		this.securityId = securityId;
		this.transactionType = transactionType;
		this.transaction_date = transaction_date;
		this.marketValue = marketValue;
		this.priority_flag = priority_flag;
	}
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public boolean isSell() {
		return transactionType.equals("SELL");
	}
	
	public boolean isWithdraw() {
		return transactionType.equals("WITHDRAW");
	}
	
	public String[] getSummary() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return new String[] {clientId,transactionType,formatter.format(transaction_date),priority_flag.toString(),Double.toString(processing_fee)};
	}
	
	

}

package com.app.account;

public class AccountType {
	private int typeId; // primary key
	private String type; // not null, unique
	public AccountType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
	public AccountType(String string) {
		if(string.equals("Checking")) {
			this.typeId = 1;
			this.type = string;
		}
		if(string.equals("Savings")) {
			this.typeId = 2;
			this.type = string;
		}
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "AccountType [typeId=" + typeId + ", type=" + type + "]";
	}
	
}

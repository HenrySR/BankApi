package com.app.account;

public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique
	public AccountStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public AccountStatus(String string) {
		if(string.equals("Pending")) {
			this.statusId = 1;
			this.status ="Pending";
		}
		if(string.equals("Open")) {
			this.statusId = 2;
			this.status ="Open";
		}
		if(string.equals("Closed")) {
			this.statusId = 3;
			this.status ="Closed";
		}
		if(string.equals("Denied")) {
			this.statusId = 4;
			this.status ="Denied";
		}
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AccountStatus [statusId=" + statusId + ", status=" + status + "]";
	}
	
}

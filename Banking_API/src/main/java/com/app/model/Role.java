package com.app.model;

public class Role {
	 private int roleId; // primary key
	 private String role; // not null, unique
	 
	 
	public Role(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	public Role(int roleId) {
		if(roleId==1) {
			this.roleId = roleId;
			this.role = "Customer";
		}
		if(roleId==2) {
			this.roleId = roleId;
			this.role = "Employee";
		}
		if(roleId==3) {
			this.roleId = roleId;
			this.role = "Admin";
		}
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	 
}

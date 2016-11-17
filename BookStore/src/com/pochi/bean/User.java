package com.pochi.bean;

import java.util.Date;

public class User {
	private String id;
	private String name;
	private String password;
	private Date birthday;
	private String email;
	private String cellphone;
	private String address;
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String id, String name, String password, Date birthday,
			String email, String cellphone, String address) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.birthday = birthday;
		this.email = email;
		this.cellphone = cellphone;
		this.address = address;
	}
	
	
}


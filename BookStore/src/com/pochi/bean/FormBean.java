package com.pochi.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormBean {
	private String name;
	private String password;
	private String password2;
	private String birthday; 
	private String email;
	private String cellphone;
	private String id;
	private String address;
	private Map<String, String>errors=new LinkedHashMap<String, String>();
	
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
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FormBean() {
		super();
	}
	
	public FormBean(String name, String password, String password2,
			String birthday, String email, String cellphone, String id,
			String address, Map<String, String> errors) {
		super();
		this.name = name;
		this.password = password;
		this.password2 = password2;
		this.birthday = birthday;
		this.email = email;
		this.cellphone = cellphone;
		this.id = id;
		this.address = address;
		this.errors = errors;
	}
	public boolean isValide(){
		boolean flag=true;
		/*
	     * validate方法负责校验表单输入项
	     * 表单输入项校验规则：
	     *         private String userName; 用户名不能为空，并且要是3-8的字母数字和下划线 abcdABcd 
	     *         private String userPwd; 密码不能为空，并且要是6-16的数字字母或者下划线
	     *         private String confirmPwd; 两次密码要一致
	     *         private String email; 可以为空，不为空要是一个合法的邮箱 
	     *         private String birthday; 可以为空，不为空时，要是一个合法的日期
	     */
		if (this.name==null||this.name.trim().equals("")) {
			flag=false;
			errors.put("username", "用户名不能为空");
		}else if (!this.name.matches("\\w{3,16}")) {
			flag=false;
			errors.put("username", "用户名必须由3-16位字母、数字或下划线_组成");
		}
		if(this.password==null||this.password.trim().equals("")){
			flag=false;
			errors.put("password", "密码不能为空");
		}else if(!this.password.matches("\\w{6,16}")){
			flag=false;
			errors.put("password", "密码必须由6-16位字母、数字或下划线_组成");
		}
		if (!this.password2.equals(this.password)) {
			flag=false;
			errors.put("password2", "两次输入密码必须一致");
		}
		if(this.email!=null&&!this.email.trim().equals("")){
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
				flag=false;
				errors.put("email", "请输入格式正确的email");
			}
		}
		if(this.birthday!=null&&!this.birthday.trim().equals("")){
			try{
				DateFormat format=DateFormat.getDateInstance();
				Date da=format.parse(this.birthday);
			}catch(Exception e){
				flag=false;
				errors.put("birthday", "生日请输入正确格式的日期");
			}
		}
		
		if(this.address==null||this.address.trim().equals("")){
			flag=false;
			errors.put("address", "收货地址不能为空");
		}
		if (this.cellphone==null||this.cellphone.trim().equals("")) {
			flag=false;
			errors.put("cellphone", " 联系电话不能为空");
		}else if (!this.cellphone.matches("[1]\\d{10}")) {
			flag=false;
			errors.put("cellphone", "请输入正确的手机号码");
		}
		
		return flag;
		
	}
}

